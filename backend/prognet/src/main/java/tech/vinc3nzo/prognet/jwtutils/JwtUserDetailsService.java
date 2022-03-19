package tech.vinc3nzo.prognet.jwtutils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.vinc3nzo.prognet.jpa.repositories.UserRepository;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException
    {
        var user = userRepository.findAll().parallelStream()
                .filter(u -> u.getUsername().equals(username))
                .toList();
        if (user.size() == 1) {
            return new User(user.get(0).getUsername(), user.get(0).getPassword(),
                    new ArrayList<>());
        }
        else {
            throw new UsernameNotFoundException(
                    "There is no user with such name in the database: '"
                    + username + "'");
        }
    }
}
