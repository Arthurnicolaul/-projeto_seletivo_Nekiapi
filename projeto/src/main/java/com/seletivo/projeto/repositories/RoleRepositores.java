package com.seletivo.projeto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seletivo.projeto.enums.RoleEnum;
import com.seletivo.projeto.model.Role;

@Repository
public interface RoleRepositores extends JpaRepository<Role, Integer> {

  Optional<Role> findByName(RoleEnum roleUser);
  
}