package br.com.fiap.foodshare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.foodshare.dto.CredencialDTO;
import br.com.fiap.foodshare.dto.UsuarioDTO;
import br.com.fiap.foodshare.dto.responseDTO.UsuarioResponseDTO;
import br.com.fiap.foodshare.exception.RestNotFoundException;
import br.com.fiap.foodshare.models.Token;
import br.com.fiap.foodshare.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    @Operation(summary = "Realiza o login do usuário")
    public ResponseEntity<Token> login(@Valid @RequestBody CredencialDTO credencial) {
        Token token = usuarioService.login(credencial);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/registrar")
    @Operation(summary = "Registra um novo usuário")
    public ResponseEntity<UsuarioResponseDTO> registrar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioResponseDTO responseDTO = usuarioService.registrar(usuarioDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{email}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable String email,
                                                               @RequestBody UsuarioDTO usuarioDTO) {
        try {
            UsuarioResponseDTO usuarioAtualizado = usuarioService.atualizar(email, usuarioDTO);
            return ResponseEntity.ok(usuarioAtualizado);
        } catch (RestNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    
}
