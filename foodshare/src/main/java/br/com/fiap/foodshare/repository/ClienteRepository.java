package br.com.fiap.foodshare.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.foodshare.models.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
    Page<Cliente> findAll(Pageable pageable);

}
