package br.com.fiap.foodshare.models;

import java.time.LocalDateTime;

public record Token(
                String token,
                String type,
                String prefix,
                LocalDateTime ultimoAcesso,
                Long cliente) {
}
