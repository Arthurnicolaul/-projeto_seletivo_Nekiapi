package com.seletivo.projeto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seletivo.projeto.dto.LoginRequestDTO;
import com.seletivo.projeto.dto.SignupResponseDTO;
import com.seletivo.projeto.exception.ApiError;
import com.seletivo.projeto.exception.UsuarioException;
import com.seletivo.projeto.model.Usuario;
import com.seletivo.projeto.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
  @Autowired
  private UsuarioService usuarioService;
  
  
	@GetMapping
  @SecurityRequirement(name = "token")
	public ResponseEntity<List<Usuario>> getAll() {
		return new ResponseEntity<>(usuarioService.getAll(), HttpStatus.OK);
	}

  @PostMapping("/sign-out")
	@SecurityRequirement(name = "token")
	public ResponseEntity<String> logoutUser() {
		return ResponseEntity.ok(usuarioService.logoutUser());
	}

  @PostMapping("/sign-in")
	@Operation(summary = "Sign In Service", description = "Sign In Service", responses = {
			@ApiResponse(responseCode = "200", description = "Successfully Singned In!", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SignupResponseDTO.class))),
			@ApiResponse(responseCode = "400", ref = "BadRequest"),
			@ApiResponse(responseCode = "401", ref = "badcredentials"),
			@ApiResponse(responseCode = "422", ref = "unprocessableEntity"),
			@ApiResponse(responseCode = "500", ref = "internalServerError") })
	public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {
		try {
			SignupResponseDTO signupResponse = usuarioService.authenticateUser(loginRequest);
			System.out.println(signupResponse);
			return ResponseEntity.ok().header("Authorization", signupResponse.getAccessToken()).body(signupResponse);
		} catch (UsuarioException e) {
			return ResponseEntity.unprocessableEntity().body(
					new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, "Unprocessable Entity", e.getLocalizedMessage()));
		}
	}
  @SecurityRequirement(name = "token")
  @GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
		Usuario usuarioResponse = usuarioService.getUsuarioById(id);
		if (null == usuarioResponse)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
	}
  @PostMapping
	public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario) {
		if (usuario == null)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(usuarioService.saveUsuario(usuario), HttpStatus.OK);
	}
  @PutMapping("/{id}")
  @SecurityRequirement(name = "token")
	public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario, @PathVariable Long id) {
		Usuario verificar = usuarioService.getUsuarioById(id);
		if (verificar == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<>(usuarioService.updateusuario(usuario, id), HttpStatus.OK);
	}
  @DeleteMapping("/{id}")
  @SecurityRequirement(name = "token")
	public ResponseEntity<Boolean> dellUsuario(@PathVariable Long id) {
		if (usuarioService.getUsuarioById(id) != null) {
			Boolean resp = usuarioService.deleteUsuario(id);
			if (resp)
				return new ResponseEntity<>(resp, HttpStatus.OK);
			else
				return new ResponseEntity<>(resp, HttpStatus.NOT_MODIFIED);
		} else
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
	}

}
