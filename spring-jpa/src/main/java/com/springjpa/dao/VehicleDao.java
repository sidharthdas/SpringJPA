package com.springjpa.dao;

import java.util.List;
import java.util.Optional;

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
	Optional<Vehicle> findByVehicleName(@Param("vehicleName")String vehicleName);
	
	@Modifying
	@Query(value = "DELETE FROM Vehicle WHERE vehicleName = :vehicleName", nativeQuery = true)
	void deleteVechile(@Param("vehicleName")String vehicleName);
	
	@Modifying
	@Query(value = "UPDATE Vehicle SET vehicleDesc = :vehicleDesc WHERE vehicleName = :vehicleName", nativeQuery = true)
	int updateVehicleDesc(@Param("vehicleDesc")String vehicleDesc, @Param("vehicleName")String vehicleName);
	
	@Query(value = " SELECT * FROM Vehicle", nativeQuery = true)
	List<Vehicle> allVehicle();
	
	@Modifying
	@Query(value = "DELETE FROM Vehicle WHERE  vechileManufactureYear = :vechileManufactureYear", nativeQuery = true)
	void deleteVehiclesOfGivenDate(@Param("vechileManufactureYear") String vechileManufactureYear);
	
	@Modifying
	@Query(value = "UPDATE Vehicle SET vehiclePrice = :vehiclePrice WHERE vehicleName = :vehicleName ", nativeQuery = true)
	int updatePriceOfVehicle(@Param("vehicleName")String vehicleName, @Param("vehiclePrice")double updatedPrice);
}
