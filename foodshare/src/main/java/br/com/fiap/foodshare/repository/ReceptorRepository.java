package br.com.fiap.foodshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.foodshare.models.Receptor;



public interface ReceptorRepository extends JpaRepository<Receptor, Long>{
    
}
