package br.com.fiap.foodshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.foodshare.models.Cliente;



public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
}
