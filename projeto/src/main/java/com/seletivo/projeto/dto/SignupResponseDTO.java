package com.seletivo.projeto.dto;

public class SignupResponseDTO {
  private String accessToken;
  private String type = "Bearer";
  private Integer id;
  private String username;

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

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCpf() {
    return username;
  }

  public void setCpf(String username) {
    this.username = username;
  }

 

  public SignupResponseDTO(String accessToken, Integer id, String username) {
    this.accessToken = accessToken;
    this.id = id;
    this.username = username;
    
  }
}
