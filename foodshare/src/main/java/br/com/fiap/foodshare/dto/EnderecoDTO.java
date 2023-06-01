package br.com.fiap.foodshare.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EnderecoDTO {

    private Long id;

    @NotNull
    private Long cliente;

    @NotNull
    @Size(min = 8, max = 8, message = "CEP deve ter 8 caracteres")
    private String cep;

    @NotNull
    @Size(max = 60, message = "Bairro pode ter no máximo 60 caracteres")
    private String bairro;

    @NotNull
    @Size(max = 90, message = "Logradouro pode ter no máximo 90 caracteres")
    private String logradouro;

    @NotNull
    @Size(max = 90, message = "Bairro pode ter no máximo 60 caracteres")
    private String numero;

    @Size(max = 90, message = "Bairro pode ter no máximo 60 caracteres")
    private String complemento;

    @NotNull
    @Size(max = 30, message = "Cidade pode ter no máximo 30 caracteres")
    private String cidade;

    @NotNull
    @Size(max = 60, message = "Estado pode ter no máximo 60 caracteres")
    private String estado;

    @NotNull
    @Size(min = 2, max = 2, message = "UF deve ter 2 caracteres")
    private String uf;

    @NotNull
    @Size(max = 50, message = "Latitude pode ter no máximo 50 caracteres")
    private String latitude;

    @NotNull
    @Size(max = 50, message = "Longetitude pode ter no máximo 50 caracteres")
    private String longitude;
}
