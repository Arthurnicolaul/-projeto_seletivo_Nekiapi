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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seletivo.projeto.model.Skill;
import com.seletivo.projeto.model.Usuario;
import com.seletivo.projeto.repositories.UsuarioRepository;
import com.seletivo.projeto.services.SkillService;
import com.seletivo.projeto.services.UsuarioService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController 
@RequestMapping("/Skill")
public class SkillController {
  @Autowired
  private SkillService skillService;
  @Autowired
  private UsuarioService usuarioService;
  @Autowired
  private UsuarioRepository usuarioRepository;


	@GetMapping
	public ResponseEntity<List<Skill>> getAllSkill(){
		return new ResponseEntity<>(skillService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
  @SecurityRequirement(name = "token")
	public ResponseEntity<Skill> getSkillById(@PathVariable Long id){
		Skill skillResponse = skillService.getSkillById(id);
		if (null == skillResponse)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else
		return new ResponseEntity<>(skillResponse, HttpStatus.OK);
	}
	         
	@GetMapping("/Usuario/{idSkill}")
  @SecurityRequirement(name = "token")
	public ResponseEntity<Skill> getSkillByIdUsuario(@PathVariable Long idUsuario){
		System.out.println(idUsuario);
		Skill skillResponse = skillService.getSkillByIdUsuario(idUsuario);
		if (null == skillResponse)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(skillResponse, HttpStatus.OK);
	}
@PostMapping("/{idUsuario}")
	public ResponseEntity<Skill> saveSkill(@RequestBody Skill skill, @PathVariable Long idUsuario) {
		Usuario usuario = usuarioService.getUsuarioById(idUsuario);
		if(usuario != null) {
			return new ResponseEntity<>(skillService.saveSkill(skill), HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{idUsuario}")
  @SecurityRequirement(name = "token")
	public ResponseEntity<Skill> updateSkill(@RequestBody Skill skill, @PathVariable Long idUsuario) {
		Usuario usuario = usuarioService.getUsuarioById(idUsuario);
		Skill verificar = skillService.getSkillByIdUsuario(idUsuario);
		if(verificar == null && usuario == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
		}
		else 
			return new ResponseEntity<>(skillService.updateSkill(skill), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
  @SecurityRequirement(name = "token")
	public ResponseEntity<Boolean> dellSkill(@PathVariable Long id) {
		if (skillService.getSkillById(id) != null) {
			Boolean resp = skillService.deleteSkill(id);
			if (resp)
				return new ResponseEntity<>(resp, HttpStatus.OK);
			else
				return new ResponseEntity<>(resp, HttpStatus.NOT_MODIFIED);
		}
		else 
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);	
	}
}
