//package ru.kata.spring.boot_security.demo.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import ru.kata.spring.boot_security.demo.model.Role;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
//import ru.kata.spring.boot_security.demo.repositories.UserRepository;
//
//@Service
//public class RegistrationServiceImpl implements RegistrationService{
//
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;
//
//
//    @Autowired
//    public RegistrationServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Transactional
//    public void register(User user) {
//        Role roleAdmin = roleRepository.findById(1L).get();
//        Role roleUser = roleRepository.findById(2L).get();
//
//        if (user.getUsername().equals("admin")) {
//            user.addRole(roleAdmin);
//            user.addRole(roleUser);
//        }
//        user.addRole(roleUser);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//    }
//}
