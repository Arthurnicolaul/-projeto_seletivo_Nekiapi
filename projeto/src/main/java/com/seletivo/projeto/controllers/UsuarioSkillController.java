package com.seletivo.projeto.controllers;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.seletivo.projeto.model.UsuarioSkill;
import com.seletivo.projeto.services.UsuarioService;
import com.seletivo.projeto.services.UsuarioSkillService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/UsuarioSkill")
public class UsuarioSkillController {

  @Autowired
  private UsuarioSkillService usuarioSkillService;
  @Autowired
  private UsuarioService usuarioService;

  @GetMapping
	public ResponseEntity<List<UsuarioSkill>> getAllUsarioSkill(){
		return new ResponseEntity<>(usuarioSkillService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
  @SecurityRequirement(name = "token")
	public ResponseEntity<UsuarioSkill> getUsuarioSkillById(@PathVariable Long id){
		UsuarioSkill usuarioskillResponse = usuarioSkillService.getUsuarioSkillById(id);
		if (null == usuarioskillResponse)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else
		return new ResponseEntity<>(usuarioskillResponse, HttpStatus.OK);
	}
	         
	@GetMapping("/skillByUsuario/{idUsuarioSkill}")
  @SecurityRequirement(name = "token")
	public ResponseEntity<UsuarioSkill> getUsuarioSkillByIdUsuario(@PathVariable Long idUsuario){
		System.out.println(idUsuario);
		UsuarioSkill usuarioskillResponse = usuarioSkillService.getUsuarioSkillById(idUsuario);
		if (null == usuarioskillResponse)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(usuarioskillResponse, HttpStatus.OK);
	}
	
	@PostMapping("/register")
  @SecurityRequirement(name = "token")
  public ResponseEntity<Object> insert(@Valid @RequestBody UsuarioSkill usuarioSkill) throws IOException {
    try {
      UsuarioSkill response = usuarioSkillService.registerUsuarioSkill(usuarioSkill);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
          .buildAndExpand(response.getId())
          .toUri();
      return ResponseEntity.created(uri).body(response);
    } catch (RuntimeException e) {
      return ResponseEntity.unprocessableEntity()
          .body(HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

	
	@PutMapping("/{idUsuario}")
  @SecurityRequirement(name = "token")
	public ResponseEntity<UsuarioSkill> updateUsuarioSkill(@RequestBody UsuarioSkill usuarioskill, @PathVariable Long id) {
		UsuarioSkill usuarioSkill = usuarioSkillService.getUsuarioSkillById(id);
		if(usuarioSkill == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
		}
		else 
			return new ResponseEntity<>(usuarioSkillService.updateUsuarioSkill(usuarioskill), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
  @SecurityRequirement(name = "token")
	public ResponseEntity<Boolean> dellUsuarioSkill(@PathVariable Long id) {
		if (usuarioSkillService.getUsuarioSkillById(id) != null) {
			Boolean resp = usuarioSkillService.deleteUsuarioSkill(id);
			if (resp)
				return new ResponseEntity<>(resp, HttpStatus.OK);
			else
				return new ResponseEntity<>(resp, HttpStatus.NOT_MODIFIED);
		}
		else 
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);	
	}
}
