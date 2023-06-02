package br.com.fiap.foodshare.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.foodshare.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Page<Cliente> findAll(Pageable pageable);

	@Query(value = "SELECT c.* FROM FS_T_CLIENTE c INNER JOIN FS_T_ENDERECO e ON c.ID_CLIENTE = e.ID_CLIENTE WHERE 6371 * 2 * ASIN(SQRT(POWER(SIN((:latitude - e.NR_LATITUDE) * 3.141592653589793 / 180 / 2), 2) + COS(:latitude * 3.141592653589793 / 180) * COS(e.NR_LATITUDE * 3.141592653589793 / 180) * POWER(SIN((:longitude - e.NR_LONGITUDE) * 3.141592653589793 / 180 / 2), 2))) <= :raio", nativeQuery = true)
	List<Cliente> buscarClientesNoRaio(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("raio") Double raio);







}
