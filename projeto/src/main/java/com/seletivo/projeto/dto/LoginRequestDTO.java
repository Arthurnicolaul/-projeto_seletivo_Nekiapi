package com.seletivo.projeto.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {
  @NotBlank
  private String username;

  @NotBlank
  private String password;

  public String getUsename() {
    return username;
  }

  public void setUsename(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
