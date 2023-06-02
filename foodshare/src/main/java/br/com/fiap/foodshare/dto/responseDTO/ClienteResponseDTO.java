package br.com.fiap.foodshare.dto.responseDTO;

import br.com.fiap.foodshare.models.Cliente;
import br.com.fiap.foodshare.models.Doador;
import br.com.fiap.foodshare.models.Receptor;
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
public class ClienteResponseDTO {

    private Long id;

    @NotNull
    @Column(name = "NR_CPF")
    @Pattern(regexp = "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}", message = "CPF inválido. Formato esperado: XXX.XXX.XXX-XX")
    private String cpf;

    @Column(name = "DS_NOME_COMPLETO")
    @Size(max = 90, message = "O nome deve ter no máximo 90 caracteres")
    private String nomeCompleto;

    @NotNull(message = "O perfil é obrigatório")
    private Perfil perfil;

    private EnderecoResponseDTO endereco;

    public ClienteResponseDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.cpf = cliente.getCpf();
        this.nomeCompleto = cliente.getNomeCompleto();
        this.perfil = cliente.getTipo();
        this.endereco = (cliente.getEndereco() == null ? new EnderecoResponseDTO(null)
                : new EnderecoResponseDTO(cliente.getEndereco()));
    }

    public ClienteResponseDTO(Receptor cliente) {
        this.id = cliente.getId();
        this.cpf = cliente.getCpf();
        this.nomeCompleto = cliente.getNomeCompleto();
        this.perfil = cliente.getTipo();
        this.endereco = (cliente.getEndereco() == null ? new EnderecoResponseDTO(null)
                : new EnderecoResponseDTO(cliente.getEndereco()));
    }

    public ClienteResponseDTO(Doador cliente) {
        this.id = cliente.getId();
        this.cpf = cliente.getCpf();
        this.nomeCompleto = cliente.getNomeCompleto();
        this.perfil = cliente.getTipo();
        this.endereco = (cliente.getEndereco() == null ? new EnderecoResponseDTO(null)
                : new EnderecoResponseDTO(cliente.getEndereco()));
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", cpf=" + cpf + ", nomeCompleto=" + nomeCompleto + ", Endereco: " + endereco
                + "]";
    }

}
