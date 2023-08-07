package com.seletivo.projeto.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seletivo.projeto.model.UsuarioSkill;
import com.seletivo.projeto.repositories.UsuarioRepository;
import com.seletivo.projeto.repositories.UsuarioSkillRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioSkillService {
  @Autowired
  private UsuarioSkillRepository usuarioskillRepository;
  @Autowired
  private UsuarioRepository usuarioRepository;

  public List<UsuarioSkill> getAll() {
		return usuarioskillRepository.findAll();
	}
  public UsuarioSkill getUsuarioSkillByIdUsuario(Long id) {
		return usuarioskillRepository.buscarUsuarioSkillUsuario(id);
	}
	
	public UsuarioSkill getUsuarioSkillById(Long id) {
		return usuarioskillRepository.findById(id).orElse(null);
	}
	public UsuarioSkill saveUsuarioSkill(UsuarioSkill usuarioskill) {
		return usuarioskillRepository.save(usuarioskill);

	}
	public UsuarioSkill updateUsuarioSkill(UsuarioSkill usuarioskill) {
		return usuarioskillRepository.save(usuarioskill);
	}
	public boolean deleteUsuarioSkill(Long id) {
		usuarioskillRepository.deleteById(id);
		UsuarioSkill usuarioDeletado = usuarioskillRepository.findById(id).orElse(null);
		if (null == usuarioDeletado) {
			return true;
		} else {
			return false;
		}
	}
	public UsuarioSkillService(UsuarioSkillRepository usuarioskillRepository, UsuarioRepository usuarioRepository) {
        this.usuarioskillRepository = usuarioskillRepository;
        this.usuarioRepository = usuarioRepository;
    }

     @Transactional
		public UsuarioSkill registerUsuarioSkill(UsuarioSkill usuarioskill) throws IOException {

    if (usuarioskillRepository.existsByNomeIgnoreCase(usuarioskill.getLevel())) {
        throw new RuntimeException("name already exists, " + usuarioskill.getLevel());
    }

    UsuarioSkill s = new UsuarioSkill();
    s.setLevel(usuarioskill.getLevel());
    s = usuarioskillRepository.save(s);

    return s;
}

}
