package com.itransition.lobach.simpleuserapp.service;

import com.itransition.lobach.simpleuserapp.domain.Role;
import com.itransition.lobach.simpleuserapp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role saveRole(Role role) {
        if(roleRepository.getRoleByRole(role.getRole()) == null)
            return roleRepository.save(role);
        else return null;
    }

    public Role getRoleById(Long id) {
        if(id != null){
            return roleRepository.getRoleById(id);
        }
        return null;
    }
}
