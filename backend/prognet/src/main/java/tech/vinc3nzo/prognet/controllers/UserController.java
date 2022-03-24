package tech.vinc3nzo.prognet.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import tech.vinc3nzo.prognet.jpa.models.User;
import tech.vinc3nzo.prognet.jpa.repositories.UserRepository;
import tech.vinc3nzo.prognet.jwtutils.config.ServiceSettings;
import tech.vinc3nzo.prognet.rspentities.CommonResponseObject;
import tech.vinc3nzo.prognet.rspentities.PhotoSet;
import tech.vinc3nzo.prognet.rspentities.UserResponseEntity;
import tech.vinc3nzo.prognet.rspentities.util.ResultCode;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping(path = "/api")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepository userRepository;

    /**
     * Request mapping to get users from the database with optional parameters.
     *
     * @param principal user, whose client made a request (automatically initialized by Spring)
     * @param count     a number of users in each abstract "page"
     * @param page      a number of the page to return (starting from 1)
     * @param term      a username of the user to search for
     * @return an instance of the {@link CommonResponseObject},
     * wrapped in a {@link ResponseEntity} (on the client's perspective
     * it is just a JSON with a response code and some other data)
     */
    @GetMapping("/users")
    public ResponseEntity<CommonResponseObject> all(Principal principal,
                                                    @RequestParam(required = false) Long count,
                                                    @RequestParam(required = false) Long page,
                                                    @RequestParam(required = false) String term)
    {
        // the requester's user object is needed. So go ahead and retrieve it.
        List<User> requester = userRepository.findAll().parallelStream()
                .filter(u -> Objects.equals(u.getUsername(), principal.getName()))
                .toList();
        if (requester.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }
        User currentUser = requester.get(0); // requester

        // process the request based of query parameters presence

        // if there are no optional parameters present
        // the code here is quite robust, but it is easy to read and understand it
        if (term == null) {
            if (count == null && page == null) {
                return usersWithPageParameters(currentUser, ServiceSettings.USERS_PER_PAGE_COUNT,
                        ServiceSettings.FIRST_PAGE);
            }
            if (count != null && page != null) {
                return usersWithPageParameters(currentUser, count, page);
            }
            if (count != null && page == null) {
                return usersWithPageParameters(currentUser, count, ServiceSettings.FIRST_PAGE);
            }
            if (count == null && page != null) {
                return usersWithPageParameters(currentUser, ServiceSettings.USERS_PER_PAGE_COUNT, page);
            }
        }

        // term != null
        return usersWithUserParameter(currentUser, term);
    }

    private ResponseEntity<CommonResponseObject> usersWithPageParameters(@NonNull User user,
                                                                         @NonNull Long count,
                                                                         @NonNull Long page)
    {
        List<UserResponseEntity> users = userRepository.findAll().parallelStream()
                .skip((page - 1) * count)
                .map(u -> new UserResponseEntity(
                        u.getUsername(),
                        u.getId(),
                        new PhotoSet(u.getSmall(), u.getLarge()),
                        u.getStatus(),
                        user.isFollowing(u)))
                .toList();

        return ResponseEntity.ok(
                new CommonResponseObject(
                        Map.of("users", users,
                                "totalCount", userRepository.findAll().size()),
                        List.of(),
                        List.of(),
                        ResultCode.SUCCESS));
    }

    private ResponseEntity<CommonResponseObject> usersWithUserParameter(@NonNull User user,
                                                                        @NonNull String term)
    {
        UserResponseEntity requestedUser = userRepository.findAll().parallelStream()
                .filter(u -> Objects.equals(u.getUsername(), term))
                .map(u -> new UserResponseEntity(
                        u.getUsername(),
                        u.getId(),
                        new PhotoSet(u.getSmall(), u.getLarge()),
                        u.getStatus(),
                        user.isFollowing(u)))
                .findFirst()
                .orElse(null);

        if (requestedUser == null) {
            return ResponseEntity.ok(
                    new CommonResponseObject(
                            Map.of("totalCount", 0),
                            List.of("User not found."),
                            List.of(),
                            ResultCode.SUCCESS));
        }

        return ResponseEntity.ok(
                new CommonResponseObject(
                        Map.of("users", List.of(requestedUser),
                                "totalCount", 1),
                        List.of(),
                        List.of(),
                        ResultCode.SUCCESS));
    }
}
