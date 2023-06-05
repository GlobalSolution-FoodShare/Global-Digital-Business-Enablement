package br.com.fiap.foodshare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.foodshare.services.TokenService;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TokenController {

    @Autowired
    TokenService tokenService;

    @GetMapping("/validar-token")
    public ResponseEntity<?> validarToken(@RequestHeader("Authorization") String authorizationHeader) {
        String token = getTokenFromAuthorizationHeader(authorizationHeader);
        
        if (token == null) {
            // Token ausente ou inválido
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        if (tokenService.validarToken(token)) {
            // Token válido
            return ResponseEntity.ok().build();
        } else {
            // Token expirado ou inválido
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
    private String getTokenFromAuthorizationHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}