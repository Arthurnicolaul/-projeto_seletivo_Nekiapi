package com.seletivo.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seletivo.projeto.model.UsuarioSkill;
@Repository
public interface UsuarioSkillRepository extends JpaRepository<UsuarioSkill, Long> {

  boolean existsByNomeIgnoreCase(int level);

  UsuarioSkill buscarUsuarioSkillUsuario(Long id);

  
  
}
