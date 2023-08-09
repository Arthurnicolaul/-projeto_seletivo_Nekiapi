package com.seletivo.projeto.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.seletivo.projeto.dto.LoginRequestDTO;
import com.seletivo.projeto.dto.SignupResponseDTO;
import com.seletivo.projeto.enums.RoleEnum;
import com.seletivo.projeto.exception.UsuarioException;
import com.seletivo.projeto.model.Role;
import com.seletivo.projeto.model.Usuario;
import com.seletivo.projeto.repositories.RoleRepository;
import com.seletivo.projeto.repositories.UsuarioRepository;
import com.seletivo.projeto.utils.JwtUtils;

import jakarta.transaction.Transactional;
@Service
public class UsuarioService {

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private UsuarioRepository usuarioRepository;
  @Autowired
  private RoleRepository roleRepositores;
     @Autowired
    private PasswordEncoder encoder;

  
	public List<Usuario> getAll() {
		return usuarioRepository.findAll();
	}
	
	public Usuario getUsuarioById(Long id) {
		return usuarioRepository.findById(id).orElse(null);
	}

  @Transactional
  public SignupResponseDTO authenticateUser(LoginRequestDTO loginRequest) {
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
            loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();

    String jwt = jwtUtils.generateJwtToken(userDetails);

    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return new SignupResponseDTO(jwt, userDetails.getId(), userDetails.getUsername(), roles);
  }
  @Transactional
  public String logoutUser() {
      UserDetailsImp userDetails = (UserDetailsImp) SecurityContextHolder.getContext()
              .getAuthentication()
              .getPrincipal();
      Long userId = userDetails.getId();
      return "Log out successful!";
  }
  
	public Usuario saveUsuario(Usuario usuario) {
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepositores.findByName(RoleEnum.USER)
                .orElseThrow(() -> new UsuarioException("Error: Role is not found."));
        roles.add(userRole);
        usuario.setRoles(roles);
				usuario.setSenha(encoder.encode(usuario.getSenha()));

				List<RoleEnum> rolesList = roles.stream().map(Role::getName).collect(Collectors.toList());
		return usuarioRepository.save(usuario);

	}
  public Usuario updateusuario(Usuario usuario, Long id) {
		Set<Role> roles = new HashSet<>();
        Role userRole = roleRepositores.findByName(RoleEnum.USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        Usuario usuario2 = usuarioRepository.findById(id).orElse(null);
        usuario.setSenha(usuario2.getSenha());
        usuario.setRoles(roles);
        usuario.setSenha(encoder.encode(usuario.getSenha()));
		return usuarioRepository.save(usuario);
	}
  public boolean deleteUsuario(Long id) {
		usuarioRepository.deleteById(id);
		Usuario usuarioDeletado = usuarioRepository.findById(id).orElse(null);
		if (null == usuarioDeletado) {
			return true;
		} else {
			return false;
		}
	}

}
