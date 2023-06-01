package br.com.fiap.foodshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.foodshare.models.Doador;



public interface DoadorRepository extends JpaRepository<Doador, Long>{
    
}
