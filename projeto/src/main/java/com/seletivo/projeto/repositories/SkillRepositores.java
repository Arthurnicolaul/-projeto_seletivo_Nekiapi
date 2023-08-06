package com.seletivo.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seletivo.projeto.model.Skill;
@Repository
public interface SkillRepositores extends JpaRepository<Skill, Integer> {
  
}
