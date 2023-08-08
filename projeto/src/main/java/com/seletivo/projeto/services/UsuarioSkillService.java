package com.seletivo.projeto.services;


import com.seletivo.projeto.dto.UsuarioSkillDTO.UsuarioSkillRequestDTO;
import com.seletivo.projeto.dto.UsuarioSkillDTO.UsuarioResponseUsuarioSkillDTO;
import com.seletivo.projeto.dto.UsuarioSkillDTO.SkillResponseUsuarioSkillDTO;
import com.seletivo.projeto.dto.UsuarioSkillDTO.UsuarioSkillResponseDTO;
import com.seletivo.projeto.model.Skill;
import com.seletivo.projeto.model.Usuario;
import com.seletivo.projeto.model.UsuarioSkill;
import com.seletivo.projeto.repositories.UsuarioSkillRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioSkillService {

    @Autowired
    private UsuarioSkillRepository usuarioskillRepository;

    public List<UsuarioSkillResponseDTO> findAll() {
        List<UsuarioSkill> usuarioSkills = usuarioskillRepository.findAll();
        return usuarioSkills.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

     public List<UsuarioSkillResponseDTO> findSkillsByUserId(Long userId) {
        List<UsuarioSkill> usuarioSkills = usuarioskillRepository.findByUsuarioId(userId);
        return usuarioSkills.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public UsuarioSkillResponseDTO findById(Long id) {
        Optional<UsuarioSkill> usuarioSkillOptional = usuarioskillRepository.findById(id);

        if (usuarioSkillOptional.isPresent()) {
            return mapToDTO(usuarioSkillOptional.get());
        }

        return null;
    }

    @Transactional
    public UsuarioSkillResponseDTO updateUsuarioSkill(Long id, UsuarioSkillRequestDTO usuarioskillRequestDTO) {
        Optional<UsuarioSkill> existingUsuarioSkillOptional = usuarioskillRepository.findById(id);

        if (existingUsuarioSkillOptional.isPresent()) {
            UsuarioSkill existingUsuarioSkill = existingUsuarioSkillOptional.get();
            existingUsuarioSkill.setLevel(usuarioskillRequestDTO.getLevel());

            return mapToDTO(usuarioskillRepository.save(existingUsuarioSkill));
        }

        return null;
    }

    @Transactional
    public boolean deleteUsuarioSkill(Long id) {
        Optional<UsuarioSkill> usuarioSkillOptional = usuarioskillRepository.findById(id);

        if (usuarioSkillOptional.isPresent()) {
            usuarioskillRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Transactional
    public UsuarioSkillResponseDTO registerUsuarioSkill(UsuarioSkillRequestDTO usuarioskillRequestDTO) {
        UsuarioSkill usuarioSkill = mapToEntity(usuarioskillRequestDTO);
        return mapToDTO(usuarioskillRepository.save(usuarioSkill));
    }

    private UsuarioSkillResponseDTO mapToDTO(UsuarioSkill usuarioSkill) {
        UsuarioSkillResponseDTO dto = new UsuarioSkillResponseDTO();
        dto.setId(usuarioSkill.getId());
        dto.setLevel(usuarioSkill.getLevel());

        UsuarioResponseUsuarioSkillDTO usuarioDTO = new UsuarioResponseUsuarioSkillDTO();
        usuarioDTO.setId(usuarioSkill.getUsuario().getId());
        usuarioDTO.setUsername(usuarioSkill.getUsuario().getUsername());

        dto.setUsuario(usuarioDTO);

        SkillResponseUsuarioSkillDTO skillDTO = new SkillResponseUsuarioSkillDTO();
        skillDTO.setId(usuarioSkill.getSkill().getId());
        skillDTO.setNome(usuarioSkill.getSkill().getNome());
        dto.setSkill(skillDTO);

        return dto;
    }

    private UsuarioSkill mapToEntity(UsuarioSkillRequestDTO dto) {
        UsuarioSkill usuarioSkill = new UsuarioSkill();
        usuarioSkill.setLevel(dto.getLevel());

        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuario().getId());
        usuarioSkill.setUsuario(usuario);

        Skill skill = new Skill();
        skill.setId(dto.getSkill().getId());
        usuarioSkill.setSkill(skill);

        return usuarioSkill;
    }
}
