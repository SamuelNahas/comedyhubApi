package com.comedyhub.prot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.comedyhub.prot.dto.UserDtoCreate;
import com.comedyhub.prot.model.User;
import com.comedyhub.prot.service.UserService;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login-user";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") @Validated UserDtoCreate user, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "login-user";
        }
        User authenticatedUser = userService.authenticateUser(user.getUsername(), user.getPassword());
        if (authenticatedUser != null) {
            attributes.addFlashAttribute("message", "Login successful!");
            return "redirect:/dashboard";
        } else {
            attributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/api/users/login";
        }
    }


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDtoCreate userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
