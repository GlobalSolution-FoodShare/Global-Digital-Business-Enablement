package br.com.fiap.foodshare.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {

    AGUARDANDO("Aguardando"),
    CONCLUIDO("Concluido"),
    CANCELADO("Cancelado");

    private String value;

    Status(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Status fromValue(String value) {
        for (Status status : values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Valor inválido para Status: " + value);
    }

    @JsonValue
    public String toValue() {
        return value;
    }
}
