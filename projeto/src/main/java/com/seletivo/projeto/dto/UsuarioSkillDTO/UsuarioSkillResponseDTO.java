package com.seletivo.projeto.dto.UsuarioSkillDTO;


import lombok.Data;

@Data
public class UsuarioSkillResponseDTO {
    private Long id;
    private UsuarioResponseUsuarioSkillDTO usuario;
    private SkillResponseUsuarioSkillDTO skill;
    private int level;
}

