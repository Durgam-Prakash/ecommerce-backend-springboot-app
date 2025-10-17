package com.ecommerce.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

	List<Address> findByUserId(int userId);

	Optional<Address> findByUserIdAndAddressId(int userId, int addressId);

}
