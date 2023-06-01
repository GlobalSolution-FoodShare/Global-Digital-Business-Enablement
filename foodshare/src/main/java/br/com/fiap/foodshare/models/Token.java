package br.com.fiap.foodshare.models;

public record Token(
        String token,
        String type,
        String prefix) {
}