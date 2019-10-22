package com.springjpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springjpa.model.UserDetail;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<UserDetail, Long> {

	@Transactional(timeout = 4)
	@Query(value = "SELECT * FROM UserDetail WHERE userName =:userName AND userAdd =:userAdd", nativeQuery = true)
	public List<UserDetail> fetchUser(@Param("userName") String userName, @Param("userAdd") String userAdd);

	@Query(value = "SELECT * FROM UserDetail WHERE userName = :userName", nativeQuery = true)
	public UserDetail findByUserName(@Param("userName") String userName);

	@Modifying
	@Query(value = "UPDATE UserDetail SET vehicle_vehicleId = :vehicleId WHERE userName = :userName", nativeQuery = true)
	int addVehicleToUser(@Param("userName") String userName, @Param("vehicleId") Long vehicleId);
	
	@Modifying
	@Query(value = "DELETE FROM UserDetail WHERE userName = :userName", nativeQuery = true)
	void deleteUser(@Param("userName")String userName);

}
