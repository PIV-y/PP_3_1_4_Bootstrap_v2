package ru.kata.spring.boot_security.demo.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.UserMan;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {
    private UserService userService;


    public SuccessUserHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/admin/users");
        } else if (roles.contains("ROLE_USER")) {
            httpServletResponse.sendRedirect("/users/read_profile");
        } else {
            httpServletResponse.sendRedirect("/");
        }
        System.out.println("Не правильное имя или нет такого пользователя");
    }
}