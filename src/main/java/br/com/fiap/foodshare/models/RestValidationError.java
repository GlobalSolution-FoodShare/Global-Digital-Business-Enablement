package br.com.fiap.foodshare.models;

public record RestValidationError(String field, String message) {
}