package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;


    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String showAllUsers (Model model) {
        model.addAttribute("users", userService.showUsers());
        return "all-users";
    }

    @DeleteMapping ("/delete/{id}")
    public String deleteUser (@PathVariable(value = "id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/";
    }

    @GetMapping("/addNewUser")
    public String addNewUser (@ModelAttribute("user") User user) {
        return "user-info";
    }

    @PostMapping("/saveUser")
    public String saveUser (@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/update/{id}")
    public String updateUser (@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.showUser(id));
        return "update";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable(value = "id") Long id) {
        userService.updateUser(id,user);
        return "redirect:/admin/";
    }
}
