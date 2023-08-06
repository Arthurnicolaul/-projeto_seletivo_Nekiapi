package com.seletivo.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seletivo.projeto.model.UsuarioSkill;
@Repository
public interface UsuarioSkillRepositores extends JpaRepository<UsuarioSkill, Integer> {
  
}