package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

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
    public String printUsers(ModelMap model) {
        model.addAttribute("users", userService.findAllUsers());
        return "users-list";
    }


    @GetMapping("/users/user-create")
    public String createUserForm(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "user-create";
    }

    @PostMapping("/users/user-create")
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/users";
    }


    @GetMapping("/users/user-update/{id}")
    public String updateUserForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", userService.findUserById(id));
        return "user-update";
    }

    @PatchMapping("/users/user-update/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin/users";
    }


    @GetMapping("/users/user-delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.removeUser(id);
        return "redirect:/admin/users";
    }
}
