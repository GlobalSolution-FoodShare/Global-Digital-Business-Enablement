package br.com.fiap.foodshare.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fiap.foodshare.dto.responseDTO.ClienteResponseDTO;
import br.com.fiap.foodshare.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Page<Cliente> findAll(Pageable pageable);

    @Query("SELECT c FROM Cliente c JOIN c.endereco e WHERE " +
            "6371 * acos(cos(radians(:latitude)) * cos(radians(e.latitude)) * cos(radians(e.longitude) - radians(:longitude)) + "
            +
            "sin(radians(:latitude)) * sin(radians(e.latitude))) <= :raio") // @Query("SELECT c FROM Cliente c JOIN
                                                                            // c.endereco e WHERE
    // FUNCTION('ST_Distance', FUNCTION('ST_SetSRID', FUNCTION('ST_MakePoint',
    // CONCAT(e.latitude, ' ', e.longitude)), 4326), FUNCTION('ST_SetSRID',
    // FUNCTION('ST_MakePoint', :latitude, :longitude), 4326)) <= :raio * 1000")
    List<ClienteResponseDTO> buscarClientesNoRaio(Double latitude, Double longitude, Double raio);

}
