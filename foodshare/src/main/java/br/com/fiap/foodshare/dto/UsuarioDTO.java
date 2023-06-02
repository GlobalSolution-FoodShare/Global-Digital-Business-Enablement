package br.com.fiap.foodshare.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;

    @NotNull(message = "Id do cliente é obrigatório")
    private Long idCliente;

    @Email(message = "Email em formato errado")
    @NotNull(message = "Email é obrigatório")
    private String email;

    @Size(min = 8, max = 50, message = "Senha deve conter 8 a 50 caracteres")
    private String senha;

}
