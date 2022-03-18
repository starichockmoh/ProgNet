package tech.vinc3nzo.prognet.jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tech.vinc3nzo.prognet.jpa.repositories.UserRepository;
import tech.vinc3nzo.prognet.models.CommonResponseObject;
import tech.vinc3nzo.prognet.models.util.ResultCode;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<CommonResponseObject> all() {
        return ResponseEntity.ok(
                new CommonResponseObject(
                        userRepository.findAll(),
                        List.of(),
                        List.of(),
                        ResultCode.SUCCESS));
    }
}
