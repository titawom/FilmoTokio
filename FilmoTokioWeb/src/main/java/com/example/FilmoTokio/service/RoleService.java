package com.example.FilmoTokio.service;

import org.springframework.stereotype.Service;

import com.example.FilmoTokio.entity.Role;
import com.example.FilmoTokio.repository.RoleRepository;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

}
