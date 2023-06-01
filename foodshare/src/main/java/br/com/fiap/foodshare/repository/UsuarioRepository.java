package br.com.fiap.foodshare.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.foodshare.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
    
}