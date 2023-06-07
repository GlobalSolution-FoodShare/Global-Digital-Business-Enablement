package br.com.fiap.foodshare.dto;

import br.com.fiap.foodshare.models.enums.Status;

public class StatusDTO {

    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}