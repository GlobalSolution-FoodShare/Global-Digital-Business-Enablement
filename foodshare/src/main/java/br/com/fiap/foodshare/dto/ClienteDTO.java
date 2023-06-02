package br.com.fiap.foodshare.dto;

import br.com.fiap.foodshare.models.enums.Perfil;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO {

    @NotNull
    @Column(name = "NR_CPF")
    @Pattern(regexp = "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}", message = "CPF inválido. Formato esperado: XXX.XXX.XXX-XX")
    private String cpf;

    @Column(name = "DS_NOME_COMPLETO")
    @Size(max = 90, message = "O nome deve ter no máximo 90 caracteres")
    private String nomeCompleto;

    @NotNull(message = "O perfil é obrigatório")
    private Perfil perfil;

    @NotNull
    private EnderecoDTO endereco;

    
    
}
