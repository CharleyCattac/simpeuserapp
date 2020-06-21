package com.itransition.lobach.simpleuserapp.service;

import com.itransition.lobach.simpleuserapp.domain.Role;
import com.itransition.lobach.simpleuserapp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    public static final Long USER_ID = 1L;
    public static final Long SUPER_ID = 3L;
    public static final Long ADMIN_ID = 2L;

    @Autowired
    private RoleRepository roleRepository;

    public Role saveRole(Role role) {
        return roleRepository.getRoleByRole(role.getRole()) == null
                ? roleRepository.save(role)
                : null;
    }

    public Role getRoleById(Long id) {
        if(id != null){
            return roleRepository.getRoleById(id);
        }
        return null;
    }

    public Role getUserRole() {
        return roleRepository.getRoleById(USER_ID);
    }

    public Role getSuperUserRole() {
        return roleRepository.getRoleById(SUPER_ID);
    }

    public Role getAdminRole() {
        return roleRepository.getRoleById(ADMIN_ID);
    }
}
