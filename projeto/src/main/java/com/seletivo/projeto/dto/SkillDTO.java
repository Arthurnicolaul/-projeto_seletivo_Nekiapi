package com.seletivo.projeto.dto;

  import com.seletivo.projeto.model.Skill;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillDTO {

    private Long id;
    
    private String nome;

    
    private String description;

    private String uri;

    public SkillDTO(Skill skill) {
        this.id = skill.getId();
        this.nome = skill.getNome();
        this.description = skill.getDescription();
    }
    
}

