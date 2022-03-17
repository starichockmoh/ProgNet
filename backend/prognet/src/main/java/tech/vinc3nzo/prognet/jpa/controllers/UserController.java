package tech.vinc3nzo.prognet.jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tech.vinc3nzo.prognet.jpa.models.User;
import tech.vinc3nzo.prognet.jpa.repositories.UserRepository;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> all() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
