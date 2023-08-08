package com.seletivo.projeto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seletivo.projeto.model.UsuarioSkill;
@Repository
public interface UsuarioSkillRepository extends JpaRepository<UsuarioSkill, Long> {

    List<UsuarioSkill> findByUsuarioId(Long userId);


  
}
