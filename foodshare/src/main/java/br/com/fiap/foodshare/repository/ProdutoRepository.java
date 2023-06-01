package br.com.fiap.foodshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.foodshare.models.Produto;



public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
