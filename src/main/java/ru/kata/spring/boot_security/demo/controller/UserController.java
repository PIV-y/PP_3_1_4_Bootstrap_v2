package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.UserMan;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

// Переход на форму создания нового юзера GET
    @GetMapping("/users/new")
    public String signUp (Model model) {
        model.addAttribute("user", new UserMan());
        return "sign up";
    }

    @GetMapping ("/users/edit")
    public String editUser(Model model,Authentication authentication) {
        model.addAttribute("user", userService.getUserByName(authentication.getName()));
        return "edit_by_user";
    }

    // Обновление юзера в БД по введенным данным
    @PatchMapping ("/users")
    public String update (@ModelAttribute("user") UserMan user,
                          Authentication authentication) {
        UserMan existingUser = userService.getUserByName(authentication.getName());
        if (existingUser == null) {
            return "redirect:/admin/users";
        }

        existingUser.setName(user.getName());
        existingUser.setLastName(user.getLastName());
        existingUser.setAge(user.getAge());
        existingUser.setPassword(user.getPassword());

        userService.changeByID(existingUser, existingUser.getId());
        return "redirect:/users/read_profile";
    }
    @GetMapping("/users/delete")
    public String deleteUserByID (Authentication authentication) {
        userService.removeUserById(userService.getUserByName(authentication.getName()).getId());
        return "redirect:/";
    }

// Чтение профиля авторизированного пользователя
    @GetMapping("/users/read_profile")
    public String readProfileUser(Model model, Authentication authentication) {
        model.addAttribute("user", userService.getUserByName(authentication.getName()));
        return "profile-page";
    }
}