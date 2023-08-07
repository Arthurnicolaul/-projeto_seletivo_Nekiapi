package com.seletivo.projeto.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seletivo.projeto.model.Role;
import com.seletivo.projeto.repositories.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	RoleRepository roleRepositores;
	
	public Role saveRole(Role role) {
        return roleRepositores.save(role);
    }
}