package br.com.fiap.foodshare.models;

import java.io.Serializable;

import br.com.fiap.foodshare.dto.ClienteDTO;
import br.com.fiap.foodshare.models.enums.Perfil;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TP_CLIENTE")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
@Table(name = "FS_T_CLIENTE", uniqueConstraints = { @UniqueConstraint(columnNames = { "NR_CPF" }) })
public abstract class Cliente implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private Long id;

    @Column(name = "NR_CPF")
    @Pattern(regexp = "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}", message = "CPF inválido. Formato esperado: XXX.XXX.XXX-XX")
    private String cpf;

    @Column(name = "DS_NOME_COMPLETO")
    @Size(max = 90)
    private String nomeCompleto;

    @OneToOne(mappedBy = "cliente", cascade = {CascadeType.PERSIST, CascadeType.ALL})
    private Endereco endereco;

    @OneToOne(mappedBy = "cliente")
    private Usuario usuario;

    public abstract Perfil getTipo();

    public Cliente(ClienteDTO clienteDTO) {
        this.cpf = clienteDTO.getCpf();
        this.nomeCompleto = clienteDTO.getNomeCompleto();
        this.endereco = new Endereco(clienteDTO.getEndereco());
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", cpf=" + cpf + ", nomeCompleto=" + nomeCompleto + ", Endereco: " + endereco + "]";
    }

}
