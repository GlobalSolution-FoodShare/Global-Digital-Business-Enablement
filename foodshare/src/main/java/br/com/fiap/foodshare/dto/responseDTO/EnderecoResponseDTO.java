package br.com.fiap.foodshare.dto.responseDTO;

import br.com.fiap.foodshare.models.Endereco;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EnderecoResponseDTO {

    private Long id;

    private Long cliente;

    @NotNull
    @Size(max = 90, message = "Logradouro pode ter no máximo 90 caracteres")
    private String logradouro;

    @NotNull
    @Size(max = 60, message = "Bairro pode ter no máximo 60 caracteres")
    private String bairro;

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
    @Size(min = 8, max = 8, message = "CEP deve ter 8 caracteres")
    private String cep;

    @NotNull
    @Size(max = 50, message = "Latitude pode ter no máximo 50 caracteres")
    private Double latitude;

    @NotNull
    @Size(max = 50, message = "Longetitude pode ter no máximo 50 caracteres")
    private Double longitude;

    public EnderecoResponseDTO(Endereco endereco) {
        this.id = endereco.getId();
        this.cliente = endereco.getCliente().getId();
        this.cep = endereco.getCep();
        this.bairro = endereco.getBairro();
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.cidade = endereco.getCidade();
        this.estado = endereco.getEstado();
        this.uf = endereco.getUf();
        this.latitude = endereco.getLatitude();
        this.longitude = endereco.getLongitude();
    }

}
