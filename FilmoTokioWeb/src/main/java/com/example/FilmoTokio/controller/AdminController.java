package com.example.FilmoTokio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.FilmoTokio.entity.User;
import com.example.FilmoTokio.repository.RoleRepository;
import com.example.FilmoTokio.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.servlet.http.HttpSession;

import com.example.FilmoTokio.DTO.UserDTO;
import com.example.FilmoTokio.entity.Role;

@Controller
@SuppressWarnings("unchecked")
@RequestMapping("/administrador")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    HttpSession session;

    @Autowired
    private RoleRepository roleRepository;
    
    BCryptPasswordEncoder passEncode= new BCryptPasswordEncoder(); //Método para el password

    @GetMapping("/")
    public String home() {
        return "redirect:/administrador/index";
    }

    @GetMapping("/index")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> grantedAuthority = (List<GrantedAuthority>) authentication.getAuthorities();
        String role = grantedAuthority.iterator().next().getAuthority();
        if (role.equals("ROLE_ADMIN")) {
            model.addAttribute("admin", true);
        } else {
            model.addAttribute("admin", false);
        }
        return "index";
    }

    @PostMapping("/user/save")
    public String save(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(passEncode.encode(userDTO.getPassword())); // Aquí se incripta el password del usuario
        Set<Role> roles = new HashSet<Role>();
        roles.add(roleRepository.findByName(userDTO.getRole()));
        user.setRoles(roles);
        user.setSurname(userDTO.getSurname());
        user.setUsername(userDTO.getUsername());
        userService.save(user);
        return "redirect:/administrador/index";
    }

    
    @GetMapping("/save-session") 
    public String saveSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        session.setAttribute("username", currentPrincipalName);
        List<GrantedAuthority> grantedAuthority = (List<GrantedAuthority>) authentication.getAuthorities();
        session.setAttribute("role", grantedAuthority.iterator().next().getAuthority());
        return "redirect:/administrador/index";
    }


    @GetMapping("/registration")
    public String registration(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> grantedAuthority = (List<GrantedAuthority>) authentication.getAuthorities();
        String role = grantedAuthority.iterator().next().getAuthority();
        if (role.equals("ROLE_ADMIN")) {
            model.addAttribute("admin", true);
        } else {
            model.addAttribute("admin", false);
            return "error";    
        }
        return "administrador/registration";
    }

}
