package com.seletivo.projeto.services;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seletivo.projeto.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserDetailsImp implements UserDetails {
  
  private static final long serialVersionUID = 1L;
  
  private Long id;

  private String usename;
 
  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  
  @Override
  public String getPassword() {
    return password;
  }

 @Override
  public boolean isEnabled() {
    return true;
  }


  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
  public static UserDetailsImp build(Usuario usuario) {
    List<? extends GrantedAuthority> authorities = usuario.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
        .collect(Collectors.toList());

    return new UserDetailsImp(
        usuario.getId(),
        usuario.getUsername(),
        usuario.getSenha(),
        authorities);
  }


  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserDetailsImp user = (UserDetailsImp) o;
    return Objects.equals(id, user.id);
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String getUsername() {
    // TODO Auto-generated method stub
    return usename;
  }


  public Long getId() {
    return id;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setPassword(String password) {
    this.password = password;
  }


  public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
    this.authorities = authorities;
  }

 

  
}