package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bean.City;

@Repository
public interface CityRepository extends JpaRepository<City, String>{
	

}
