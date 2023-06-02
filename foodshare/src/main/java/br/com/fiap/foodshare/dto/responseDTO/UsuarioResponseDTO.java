package br.com.fiap.foodshare.dto.responseDTO;

import br.com.fiap.foodshare.models.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioResponseDTO {

    private Long id;

    @NotNull(message = "Id do cliente é obrigatório")
    private Long idCliente;

    @Email(message = "Email em formato errado")
    @NotNull(message = "Email é obrigatório")
    private String email;

    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.idCliente = usuario.getCliente().getId();
        this.email = usuario.getEmail();
    }

    public UsuarioResponseDTO(Long id, @NotNull(message = "Id do cliente é obrigatório") Long idCliente,
            @Email(message = "Email em formato errado") @NotNull(message = "Email é obrigatório") String email) {
        this.id = id;
        this.idCliente = idCliente;
        this.email = email;
    }

}
