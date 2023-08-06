package com.seletivo.projeto.dto;

public class LoginUsuarioDTO {
  private String usename;
  private String senha;

  public String getUsername(){
    return usename;
  }
  public String getSenha(){
    return senha;
  }
  public String toString(){
    return "loginUsuarioDTO [cpf=" +  usename + ", senha" + senha + "]";
  }
}
