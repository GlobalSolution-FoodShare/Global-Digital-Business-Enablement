package br.com.fiap.foodshare.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Table(name = "FS_T_CLIENTE", uniqueConstraints = { @UniqueConstraint(columnNames = { "NR_CPF" }) })
public class Cliente implements Serializable{

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

}
