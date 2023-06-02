package br.com.fiap.foodshare.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.foodshare.dto.ClienteDTO;
import br.com.fiap.foodshare.dto.responseDTO.ClienteResponseDTO;
import br.com.fiap.foodshare.exception.RestNotFoundException;
import br.com.fiap.foodshare.models.Cliente;
import br.com.fiap.foodshare.models.Doador;
import br.com.fiap.foodshare.models.Receptor;
import br.com.fiap.foodshare.models.enums.Perfil;
import br.com.fiap.foodshare.repository.ClienteRepository;
import br.com.fiap.foodshare.repository.DoadorRepository;
import br.com.fiap.foodshare.repository.ReceptorRepository;
import br.com.fiap.foodshare.services.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ReceptorRepository receptorRepository;

    @Autowired
    private DoadorRepository doadorRepository;

    @Override
    public Page<ClienteResponseDTO> buscarTodos(Pageable pageable) {
        Page<Cliente> clientes = clienteRepository.findAll(pageable);

        return clientes.map(cliente -> new ClienteResponseDTO(cliente));
    }

    @Override
    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Cliente não localizado"));

        return new ClienteResponseDTO(cliente);
    }

    @Override
    public ClienteResponseDTO cadastrar(ClienteDTO clienteDTO) {

        Cliente cliente;

        if (clienteDTO.getPerfil() == Perfil.RECEPTOR) {
            cliente = new Receptor(clienteDTO);
            cliente.getEndereco().setCliente(cliente);
            cliente = receptorRepository.save((Receptor) cliente);
        } else {
            cliente = new Doador(clienteDTO);
            cliente.getEndereco().setCliente(cliente);
            cliente = doadorRepository.save((Doador) cliente);
        }

        return new ClienteResponseDTO(cliente);
    }

    @Override
    public ClienteResponseDTO atualizar(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Cliente não localizado"));

        cliente.setCpf(clienteDTO.getCpf());
        cliente.setNomeCompleto(clienteDTO.getNomeCompleto());
        cliente.getEndereco().setBairro(clienteDTO.getEndereco().getBairro());
        cliente.getEndereco().setLogradouro(clienteDTO.getEndereco().getLogradouro());
        cliente.getEndereco().setCep(clienteDTO.getEndereco().getCep());
        cliente.getEndereco().setCidade(clienteDTO.getEndereco().getCidade());
        cliente.getEndereco().setComplemento(clienteDTO.getEndereco().getComplemento());
        cliente.getEndereco().setEstado(clienteDTO.getEndereco().getEstado());
        cliente.getEndereco().setUf(clienteDTO.getEndereco().getUf());
        cliente.getEndereco().setLongitude(clienteDTO.getEndereco().getLongitude());
        cliente.getEndereco().setLatitude(clienteDTO.getEndereco().getLatitude());
        cliente.getEndereco().setNumero(clienteDTO.getEndereco().getNumero());

        cliente = clienteRepository.save(cliente);

        return new ClienteResponseDTO(cliente);
    }

    @Override
    public void deletar(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Cliente não localizado"));

        clienteRepository.delete(cliente);

    }

    @Override
    public List<ClienteResponseDTO> buscarPorRaioDistancia(Double latitude, Double longitude, Double raio) {
        return clienteRepository.buscarClientesNoRaio(latitude, longitude, raio);
    }

}
