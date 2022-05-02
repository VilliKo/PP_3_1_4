package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String homeAdmin() {
        return "redirect:/admin/users";
    }

    @GetMapping("/users")
    public String printUsers(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user, ModelMap model) {
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("user", userService.findByUsername(user.getUsername()));
        model.addAttribute("roles", roleService.getAllRoles());
        return "users-list";
    }

    @PostMapping("/users/user-create")
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @PatchMapping("/users/user-update/{id}")
    public String updateUser(@ModelAttribute("userEdit") User user) {
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/user-delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.removeUser(id);
        return "redirect:/admin/users";
    }
}
