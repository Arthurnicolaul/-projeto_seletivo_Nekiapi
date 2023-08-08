package com.seletivo.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seletivo.projeto.model.Skill;
@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

  boolean existsByNomeIgnoreCase(String nome);

  
}
