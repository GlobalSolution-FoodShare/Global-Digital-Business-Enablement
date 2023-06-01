package br.com.fiap.foodshare.dto.responseDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProdutoResponseDTO {


    private Long id;

    @NotNull
    @Size(max = 60, message = "O nome deve no máximo 60 caracteres")
    private String nome;

    @NotNull(message = "Peso é obrigatório")
    private Double peso;

    @NotNull
    @Size(max = 90, message = "A Descrição deve ter no máximo 90 caracteres")
    private String descricao;

}
