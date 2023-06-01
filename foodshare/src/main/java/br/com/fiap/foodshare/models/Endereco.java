package br.com.fiap.foodshare.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "FS_T_ENDERECO")
@Data
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ENDERECO")
    private Long id;

    @OneToOne
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
    private String longitude;

    @Column(name = "NR_LATITUDE")
    private String latitude;
}