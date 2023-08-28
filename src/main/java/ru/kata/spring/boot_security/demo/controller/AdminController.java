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

@Controller
public class AdminController {
    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

// Стартовая страница
    @GetMapping("/")
    public String printStart (Model model) {
        model.addAttribute("messages", "HEllO");
        return "start";
    }

// Сортировочный метод
    @GetMapping("/sort")
    public String printMyPage (Model model) {
        //Объект Authentication в Spring Security содержит информацию об аутентификации пользователя.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            if (user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
                return "redirect:/admin/users";
            } else if (user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"))) {
                return "redirect:/users/read_profile";
            }
        } else {
            System.out.println("фильтры контроллера не пройдены!");
        }
        return "redirect:/";
    }

// Получить список Пользователей GET
    @GetMapping("/admin/users")
    public String printUserList (Model model) {
        model.addAttribute("user", userService.getAllUsers());
        return "users";
    }

// Добавить пользователя POST
    @PostMapping("/admin/users")
    public String saveUser(@ModelAttribute("user") UserMan user, @RequestParam("roleName") String roleName) {
        user.setRoles(Arrays.asList(new Role(roleName)));
        userService.saveUser(user);
        System.out.println(user.toString());
        return "redirect:/admin/users";
    }

// Переход на форму создания нового юзера GET
    @GetMapping("/admin/users/new")
    public String addNewUserInfo (Model model) {
        model.addAttribute("user", new UserMan());
    return "add new user";
    }

// Получение юзера по ID для редактирования
    @PostMapping ("/admin/users/{id}/edit")
    public String editUser(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }
}