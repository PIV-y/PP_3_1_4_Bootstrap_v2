package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.AdminServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminServiceImpl adminServiceImpl;


    @Autowired
    public AdminController(AdminServiceImpl adminServiceImpl) {
        this.adminServiceImpl = adminServiceImpl;
    }


    @GetMapping("/")
    public String showAllUsers (Model model) {
        model.addAttribute("users", adminServiceImpl.showUsers());
        return "all-users";
    }

    @GetMapping ("/delete/{id}")
    public String deleteUser (@PathVariable(value = "id") Long id) {
        adminServiceImpl.deleteUserById(id);
        return "redirect:/admin/";
    }

    @GetMapping("/addNewUser")
    public String addNewUser (@ModelAttribute("user") User user) {
        return "user-info";
    }

    @PostMapping("/saveUser")
    public String saveUser (@ModelAttribute("user") User user) {
        adminServiceImpl.saveUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/update/{id}")
    public String updateUser (@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("user", adminServiceImpl.showUser(id));
        return "update";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable(value = "id") Long id) {
        adminServiceImpl.updateUser(id,user);
        return "redirect:/admin/";
    }
}
