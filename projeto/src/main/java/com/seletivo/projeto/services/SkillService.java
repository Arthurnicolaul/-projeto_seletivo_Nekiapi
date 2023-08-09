package com.seletivo.projeto.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seletivo.projeto.exception.SkillException;
import com.seletivo.projeto.model.Skill;
import com.seletivo.projeto.repositories.SkillRepository;

import jakarta.transaction.Transactional;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> findAll() {
        return skillRepository.findAll();
    }


    public Skill findById(Long id) {
        Skill skill = skillRepository.findById(id).orElseThrow(() -> new SkillException("Skill not found, ID: " + id));
        return skill;
    }

  

    @Transactional
    public Skill registerSkill(Skill skill) throws IOException {
        if (skillRepository.existsByNomeIgnoreCase(skill.getNome())) {
            throw new SkillException("Skill with name already exists: " + skill.getNome());
        }

        Skill s = new Skill();
        s.setDescription(skill.getDescription());
        s.setImagem(skill.getImagem());
        s.setNome(skill.getNome());
        s = skillRepository.save(s);

        return s;
    }

    @Transactional
    public Skill updateSkill(Long id, Skill skill) {
        Skill existingSkill = skillRepository.findById(id).orElseThrow(() -> new SkillException("Skill not found, ID: " + id));

        if (!skill.getNome().equalsIgnoreCase(existingSkill.getNome()) && skillRepository.existsByNomeIgnoreCase(skill.getNome())) {
            throw new SkillException("Skill with name already exists: " + skill.getNome());
        }

        existingSkill.setNome(skill.getNome());
        existingSkill = skillRepository.save(existingSkill);

        return existingSkill;
    }

    @Transactional
    public boolean deleteSkill(Long id) {
        Skill skill = skillRepository.findById(id).orElseThrow(() -> new SkillException("Skill not found, ID: " + id));

        skillRepository.delete(skill);

		return true;
    }


}
