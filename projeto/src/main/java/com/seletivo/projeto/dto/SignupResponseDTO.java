package com.seletivo.projeto.dto;

import java.util.List;

public class SignupResponseDTO {
  private String accessToken;
  private String type = "Bearer";
  private Long id;
  private String username;
  private List<String> roles;

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCpf() {
    return username;
  }

  public void setCpf(String username) {
    this.username = username;
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  public SignupResponseDTO(String accessToken, Long id, String username,  List<String> roles) {
    this.accessToken = accessToken;
    this.id = id;
    this.username = username;
    this.roles = roles;
  }
}
