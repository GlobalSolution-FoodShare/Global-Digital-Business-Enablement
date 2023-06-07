package br.com.fiap.foodshare.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.fiap.foodshare.dto.CredencialDTO;
import br.com.fiap.foodshare.models.Token;
import br.com.fiap.foodshare.models.Usuario;
import br.com.fiap.foodshare.repository.UsuarioRepository;
import jakarta.validation.Valid;

@Service
public class TokenService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Value("${jwt.secret}")
    String secret;

    public Token generateToken(@Valid CredencialDTO credencial) {
        Algorithm alg = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withSubject(credencial.email())
                .withIssuer("FoodShare10")
                .withExpiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                .sign(alg);
        return new Token(token, "JWT", "Bearer", null, null);
    }

    public boolean validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWT.require(algorithm).build().verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Usuario getValidateUser(String token) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var email = JWT.require(alg)
                .withIssuer("FoodShare10")
                .build()
                .verify(token)
                .getSubject();

        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new JWTVerificationException("Usuario invalido"));
    }

}