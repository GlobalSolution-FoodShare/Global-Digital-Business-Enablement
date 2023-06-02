package br.com.fiap.foodshare.models;

import br.com.fiap.foodshare.dto.ClienteDTO;
import br.com.fiap.foodshare.models.enums.Perfil;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "id_cliente")
@Table(name = "FS_T_DOADOR")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doador extends Cliente {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    public Perfil getTipo() {
        return Perfil.DOADOR;
    }

    public Doador(ClienteDTO clienteDTO) {
        super(clienteDTO);
    }
}