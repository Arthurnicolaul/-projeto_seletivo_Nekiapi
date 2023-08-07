package com.seletivo.projeto.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seletivo.projeto.model.Skill;
import com.seletivo.projeto.repositories.SkillRepository;
import com.seletivo.projeto.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class SkillService {

  @Autowired
  private SkillRepository skillRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;


  
  public List<Skill> getAll() {
		return skillRepository.findAll();
	}
	
	public Skill getSkillById(Long id) {
		return skillRepository.findById(id).orElse(null);
	}
	public Skill getSkillByIdUsuario(Long id) {
		return skillRepository.buscarSkillUsuario(id);
	}
	public Skill saveSkill(Skill skill) {
		return skillRepository.save(skill);

	}
	public Skill updateSkill(Skill skill) {
		return skillRepository.save(skill);
	}
	public boolean deleteSkill(Long id) {
		skillRepository.deleteById(id);
		Skill skillsDeletado = skillRepository.findById(id).orElse(null);
		if (null == skillsDeletado) {
			return true;
		} else {
			return false;
		}
	}
	public SkillService(SkillRepository skillRepository, UsuarioRepository usuarioRepository) {
        this.skillRepository = skillRepository;
        this.usuarioRepository = usuarioRepository;
    }
    
    @Transactional
		public Skill registerSkill(Skill skill) throws IOException {

    if (skillRepository.existsByNomeIgnoreCase(skill.getNome())) {
        throw new RuntimeException("name already exists, " + skill.getNome());
    }

    Skill s = new Skill();
    s.setNome(skill.getNome());
    s = skillRepository.save(s);

    return s;
}

}
