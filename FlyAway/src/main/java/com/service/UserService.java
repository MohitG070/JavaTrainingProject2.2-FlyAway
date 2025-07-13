package com.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.City;
import com.bean.Flight;
import com.repository.CityRepository;
import com.repository.FlightRepository;

@Service
public class UserService {

	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	public List<City> getAllCity() {
		List<City> allCity = cityRepository.findAll();
		return allCity;
	}
	
	
	public List<Flight> searchFlight(City sourceCity, City destinationCity, LocalDate dateOfTravel, int numberOfPassengers) {
		
		List<Flight> listOfFlight = flightRepository.searchBySourceDestination(sourceCity, destinationCity);
		
		return listOfFlight;
	}
	
	
	public Flight searchFlight(String flightId) {
		Optional<Flight> flight = flightRepository.findById(flightId);
		return flight.get();
	}
	
	
}
