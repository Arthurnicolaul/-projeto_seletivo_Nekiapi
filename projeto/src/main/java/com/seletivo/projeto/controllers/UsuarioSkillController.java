package com.seletivo.projeto.controllers;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import com.seletivo.projeto.dto.UsuarioSkillDTO.UsuarioSkillRequestDTO;
import com.seletivo.projeto.dto.UsuarioSkillDTO.UsuarioSkillResponseDTO;
import com.seletivo.projeto.services.UsuarioSkillService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarioSkill")
public class UsuarioSkillController {

    @Autowired
    private UsuarioSkillService usuarioSkillService;

    @GetMapping
    @SecurityRequirement(name = "token")
    public ResponseEntity<List<UsuarioSkillResponseDTO>> getAllUsuarioSkill() {
        List<UsuarioSkillResponseDTO> usuarioSkillResponseDTOs = usuarioSkillService.findAll();
        return new ResponseEntity<>(usuarioSkillResponseDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "token")
    public ResponseEntity<UsuarioSkillResponseDTO> getUsuarioSkillById(@PathVariable Long id) {
        UsuarioSkillResponseDTO usuarioskillResponse = usuarioSkillService.findById(id);
        if (usuarioskillResponse == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(usuarioskillResponse, HttpStatus.OK);
        }
    }

    @GetMapping("/skillByUsuario/{idUsuario}")
    @SecurityRequirement(name = "token")
    public ResponseEntity<List<UsuarioSkillResponseDTO>> getUsuarioSkillsByUserId(@PathVariable Long idUsuario) {
        List<UsuarioSkillResponseDTO> usuarioSkillResponseDTOs = usuarioSkillService.findSkillsByUserId(idUsuario);
        if (usuarioSkillResponseDTOs.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(usuarioSkillResponseDTOs, HttpStatus.OK);
        }
    }

    @PostMapping("/register")
    @SecurityRequirement(name = "token")
    public ResponseEntity<UsuarioSkillResponseDTO> insert(@RequestBody UsuarioSkillRequestDTO usuarioSkillRequestDTO) throws IOException {
        UsuarioSkillResponseDTO response = usuarioSkillService.registerUsuarioSkill(usuarioSkillRequestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "token")
    public ResponseEntity<UsuarioSkillResponseDTO> updateUsuarioSkill(@RequestBody UsuarioSkillRequestDTO usuarioSkillRequestDTO, @PathVariable Long id) {
        UsuarioSkillResponseDTO usuarioSkillResponseDTO = usuarioSkillService.updateUsuarioSkill(id, usuarioSkillRequestDTO);
        if (usuarioSkillResponseDTO == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        } else {
            return new ResponseEntity<>(usuarioSkillResponseDTO, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "token")
    public ResponseEntity<Boolean> deleteUsuarioSkill(@PathVariable Long id) {
        boolean deleted = usuarioSkillService.deleteUsuarioSkill(id);
        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
