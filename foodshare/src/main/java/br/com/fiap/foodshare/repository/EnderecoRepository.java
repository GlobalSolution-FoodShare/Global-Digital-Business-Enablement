package br.com.fiap.foodshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.foodshare.models.Endereco;



public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    
}
