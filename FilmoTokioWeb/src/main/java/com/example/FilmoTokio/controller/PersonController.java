package com.example.FilmoTokio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.FilmoTokio.DTO.PersonRequest;
import com.example.FilmoTokio.entity.Person;
import com.example.FilmoTokio.service.PersonService;

@SuppressWarnings("unchecked")
@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/new-person")
    public String registration(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> grantedAuthority = (List<GrantedAuthority>) authentication.getAuthorities();
        String role = grantedAuthority.iterator().next().getAuthority();
        if (role.equals("ROLE_ADMIN")) {
            model.addAttribute("admin", true);
        } else {
            model.addAttribute("admin", false);
        }
        return "person/new-person";
    }

    @PostMapping("/save-person")
    public String registration(PersonRequest personRequest) {
        
        Person person = new Person();
        person.setName(personRequest.getName());
        person.setSurname(personRequest.getSurname());
        person.setType(personRequest.getType());
        personService.createPerson(person);
        return "redirect:/administrador/index";
    }
}
