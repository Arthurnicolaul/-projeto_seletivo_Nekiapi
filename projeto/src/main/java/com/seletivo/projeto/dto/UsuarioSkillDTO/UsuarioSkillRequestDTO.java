package com.seletivo.projeto.dto.UsuarioSkillDTO;

import lombok.Data;

@Data
public class UsuarioSkillRequestDTO {
    private UsuarioRequestUsuarioSkillDTO usuario;
    private SkillRequestUsuarioSkillDTO skill;
    private int level;
}