package com.ceiba.adn.estacionamiento.infraestructura.persistencia.Repositorio.query;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ceiba.adn.estacionamiento.aplicacion.query.TiqueteActivoQuery;
import com.ceiba.adn.estacionamiento.infraestructura.persistencia.entidad.EntidadTiquete;

public interface QueryJPA extends CrudRepository<EntidadTiquete, Long> {
	
	@Query("select new com.ceiba.adn.estacionamiento.aplicacion.query.TiqueteActivoQuery(te.placa,te.fechaEntrada,te.tipoVehiculo) from EntidadTiquete te where te.estadoIngreso = true")
	List<TiqueteActivoQuery> tiquetesActivos();
	
	@Query("select te  from EntidadTiquete te where te.estadoIngreso = true and te.placa = :placa")
	EntidadTiquete buscarPorPlaca(@Param("placa") String placa);
	
	@Query("select count(*) from EntidadTiquete te where te.tipoVehiculo = 'MOTO' and te.estadoIngreso = true")
	long contarMotosActivas();
	
	@Query("select count(*) from EntidadTiquete te where te.tipoVehiculo = 'CARRO' and te.estadoIngreso = true")
	long contarCarrosActivos();

}
