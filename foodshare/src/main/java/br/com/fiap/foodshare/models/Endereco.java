package br.com.fiap.foodshare.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.fiap.foodshare.dto.EnderecoDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FS_T_ENDERECO")
@Data
@NoArgsConstructor
public class Endereco implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ENDERECO")
    private Long id;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.ALL, CascadeType.MERGE})
    @JsonIgnore
    @JoinColumn(name = "ID_CLIENTE")
    private Cliente cliente;

    @Column(name = "NR_CEP")
    private String cep;

    @Column(name = "DS_BAIRRO")
    private String bairro;

    @Column(name = "DS_LOGRADOURO")
    private String logradouro;

    @Column(name = "NR_LOGRADOURO")
    private String numero;

    @Column(name = "DS_COMPLEMENTO")
    private String complemento;

    @Column(name = "NM_CIDADE")
    private String cidade;

    @Column(name = "DS_ESTADO")
    private String estado;

    @Column(name = "SG_ESTADO")
    private String uf;

    @Column(name = "NR_LONGITUDE")
    private Double longitude;

    @Column(name = "NR_LATITUDE")
    private Double latitude;


    

    public Endereco(Long id, Cliente cliente, String cep, String bairro, String logradouro, String numero,
            String complemento, String cidade, String estado, String uf, Double longitude, Double latitude) {
        this.id = id;
        this.cliente = cliente;
        this.cep = cep;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
        this.uf = uf;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Endereco(EnderecoDTO enderecoDTO) {
        this.cep = enderecoDTO.getCep();
        this.bairro = enderecoDTO.getBairro();
        this.logradouro = enderecoDTO.getLogradouro();
        this.numero = enderecoDTO.getNumero();
        this.complemento = enderecoDTO.getComplemento();
        this.cidade = enderecoDTO.getCidade();
        this.estado = enderecoDTO.getEstado();
        this.uf = enderecoDTO.getUf();
        this.longitude = enderecoDTO.getLongitude();
        this.latitude = enderecoDTO.getLatitude();
    }




    @Override
public String toString() {
    return "Endereco [id=" + id + ", cep=" + cep + ", bairro=" + bairro + ", logradouro=" + logradouro + ", numero="
            + numero + ", complemento=" + complemento + ", cidade=" + cidade + ", estado=" + estado + ", uf=" + uf
            + ", longitude=" + longitude + ", latitude=" + latitude + "]";
}
}