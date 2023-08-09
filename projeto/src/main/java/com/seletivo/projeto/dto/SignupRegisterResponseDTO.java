package com.seletivo.projeto.dto;

import java.util.List;

import com.seletivo.projeto.enums.RoleEnum;
import com.seletivo.projeto.model.Skill;
import com.seletivo.projeto.model.Usuario;

import lombok.Data;

@Data
public class SignupRegisterResponseDTO {
  
  private Long id;

  private String username;

   private String senha;



  private List<RoleEnum> roles;

  public SignupRegisterResponseDTO(Usuario u, List<RoleEnum> role) {
    this.id = u.getId();
    this.username = u.getUsername();
    this.senha = u.getSenha();

    this.roles = role;
  }

}