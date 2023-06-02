package br.com.fiap.foodshare.services;

import br.com.fiap.foodshare.dto.CredencialDTO;
import br.com.fiap.foodshare.dto.UsuarioDTO;
import br.com.fiap.foodshare.dto.responseDTO.UsuarioResponseDTO;
import br.com.fiap.foodshare.models.Token;

public interface UsuarioService {

    Token login(CredencialDTO credencial);

    UsuarioResponseDTO registrar(UsuarioDTO usuario);

    UsuarioResponseDTO atualizar(String email, UsuarioDTO usuario);

}
