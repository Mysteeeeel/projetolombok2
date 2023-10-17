package com.lombok.controller;

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

import com.lombok.entities.Usuario;
import com.lombok.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Usuario", description = "API REST DE GERENCIAMENTO DE USUÁRIO")
@RestController
@RequestMapping("/usuario")

public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("/")
	@Operation(summary = "Busca todos os usuários")
	public ResponseEntity<Usuario> buscaTodosUsuarioControl(){
		List<Usuario> usuario = usuarioService.buscaTodosUsuarios();
		return ResponseEntity.ok(usuario);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Localiza usuário por ID")
	public ResponseEntity<Usuario> buscaUsuarioControlId(@PathVariable Long id){
		Usuario usuario = usuarioService.buscaUsuarioId(id);
		if(usuario != null) {
			return ResponseEntity.ok(usuario);
		}
		
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/")
	@Operation(summary = "Salva usuário")
	public ResponseEntity<Usuario> salvaUsuarioControl(@RequestBody @Valid Usuario usuario){
		Usuario salvaUsuario = usuarioService.salvaUsuario(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaUsuario);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Altera usuario por Id")
	public ResponseEntity<Usuario> alteraUsuarioControl(@PathVariable Long id, @RequestBody @Valid Usuario usuario){
		Usuario alteraUsuario = usuarioService.alterarUsuario(id, usuario);
		if(alteraUsuario != null) {
			return ResponseEntity.ok(usuario);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta o Usuario por id")
	public ResponseEntity<Usuario> apagaUsuarioControl(@PathVariable Long id){
		boolean apagar = usuarioService.apagarUsuario(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

}
