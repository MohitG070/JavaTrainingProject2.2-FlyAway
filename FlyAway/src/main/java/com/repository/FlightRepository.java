package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bean.City;
import com.bean.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String>{

	@Query("select flgt from Flight flgt where flgt.sourceCity = :sourceCity and flgt.destinationCity = :destinationCity")
	public List<Flight> searchBySourceDestination(City sourceCity, City destinationCity);
	
	
	
	
}
