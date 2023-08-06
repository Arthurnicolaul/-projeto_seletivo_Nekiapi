package com.seletivo.projeto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.seletivo.projeto.model.Usuario;
import com.seletivo.projeto.repositories.UsuarioRepositores;

import jakarta.transaction.Transactional;

@Service
public class UserDetailsImpService implements UserDetailsService {
  
  @Autowired
  UsuarioRepositores usuarioRepositores;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) {
    Optional<Usuario> user = usuarioRepositores.findByusername(username);
    

    return UserDetailsImp.build(user.get());
  }
}
