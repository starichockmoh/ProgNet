package tech.vinc3nzo.prognet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tech.vinc3nzo.prognet.repository.UserRepository;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    // TODO: request mappings
}
