package com.springjpa.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springjpa.model.Vehicle;
@Repository
@Transactional
public interface VehicleDao extends CrudRepository<Vehicle, Long> {
	
	@Query(value = "SELECT * FROM Vehicle WHERE vehicleName = :vehicleName", nativeQuery = true)
	Vehicle findByVehicleName(@Param("vehicleName")String vehicleName);
	
	@Modifying
	@Query(value = "DELETE FROM Vehicle WHERE vehicleName = :vehicleName", nativeQuery = true)
	void deleteVechile(@Param("vehicleName")String vehicleName);

}
