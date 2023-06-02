package br.com.fiap.foodshare.services.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fiap.foodshare.dto.CredencialDTO;
import br.com.fiap.foodshare.dto.UsuarioDTO;
import br.com.fiap.foodshare.dto.responseDTO.UsuarioResponseDTO;
import br.com.fiap.foodshare.exception.BadRequestException;
import br.com.fiap.foodshare.exception.RestNotFoundException;
import br.com.fiap.foodshare.models.Cliente;
import br.com.fiap.foodshare.models.Token;
import br.com.fiap.foodshare.models.Usuario;
import br.com.fiap.foodshare.repository.ClienteRepository;
import br.com.fiap.foodshare.repository.UsuarioRepository;
import br.com.fiap.foodshare.services.TokenService;
import br.com.fiap.foodshare.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TokenService tokenService;

    @Override
    public Token login(CredencialDTO credencial) {
        manager.authenticate(credencial.toAuthentication());
        var token = tokenService.generateToken(credencial);
        var usuario = fazBuscaEmail(credencial);

        Token tokenComUltimoAcesso = new Token(token.token(), token.type(), token.prefix(), usuario.getUltimoAcesso());

        System.out.println(tokenComUltimoAcesso);

        return tokenComUltimoAcesso;
    }

    @Override
    public UsuarioResponseDTO registrar(UsuarioDTO usuarioDTO) {
        System.out.println(usuarioDTO.getEmail());
        if (usuarioRepository.findByEmail(usuarioDTO.getEmail()).isPresent()) {
            throw new BadRequestException("Email já existe, por favor crie outro.");
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(encoder.encode(usuarioDTO.getSenha()));

        Cliente cliente = clienteRepository.findById(usuarioDTO.getIdCliente())
                .orElseThrow(() -> new RestNotFoundException("Cliente não encontrado"));

        if (cliente.getUsuario() != null) {
            throw new BadRequestException("Cliente já tem um usuário associado.");
        }

        usuario.setCliente(cliente);

        usuario = usuarioRepository.save(usuario);

        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO(usuario.getId(), cliente.getId(), usuario.getEmail());

        return responseDTO;
    }

    public Usuario fazBuscaEmail(CredencialDTO credencial) {
        var usuario = usuarioRepository.findByEmail(credencial.email()).get();

        usuario.setUltimoAcesso(LocalDateTime.now());
        return usuarioRepository.save(usuario);

    }

}
