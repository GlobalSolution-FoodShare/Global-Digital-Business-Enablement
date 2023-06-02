package br.com.fiap.foodshare.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public record CredencialDTO(String email, String senha) {

    public Authentication toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }

}