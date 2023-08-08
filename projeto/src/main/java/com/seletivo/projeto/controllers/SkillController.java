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

import com.seletivo.projeto.model.Skill;
import com.seletivo.projeto.services.SkillService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;


@RestController 
@RequestMapping("/skill")
public class SkillController {
  @Autowired
  private SkillService skillService;


	@GetMapping
	public ResponseEntity<List<Skill>> getAllSkill(){
		return new ResponseEntity<>(skillService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@SecurityRequirement(name = "token")
	public ResponseEntity<Skill> getSkillById(@PathVariable Long id){
		Skill skillResponse = skillService.findById(id);
		if (null == skillResponse)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else
		return new ResponseEntity<>(skillResponse, HttpStatus.OK);
	}

  @PostMapping("/register")
  @SecurityRequirement(name = "token")
  public ResponseEntity<Object> insert(@Valid @RequestBody Skill skill) throws IOException {
    try {
      Skill response = skillService.registerSkill(skill);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
          .buildAndExpand(response.getId())
          .toUri();
      return ResponseEntity.created(uri).body(response);
    } catch (RuntimeException e) {
      return ResponseEntity.unprocessableEntity()
          .body(HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

	         

	@PutMapping("/{id}")
	@SecurityRequirement(name = "token")
	public ResponseEntity<Skill> updateSkill(@RequestBody Skill skill, @PathVariable Long id) {
		Skill verificar = skillService.updateSkill(id, skill);
		if(verificar == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
		}
		else 
			return new ResponseEntity<>(skillService.updateSkill(id, skill), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@SecurityRequirement(name = "token")
	public ResponseEntity<Boolean> dellSkill(@PathVariable Long id) {
		if (skillService.findById(id) != null) {
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
