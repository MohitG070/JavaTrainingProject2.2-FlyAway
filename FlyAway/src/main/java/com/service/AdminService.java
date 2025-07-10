package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Admin;
import com.bean.Airline;
import com.bean.City;
import com.bean.Flight;
import com.repository.AdminRepository;
import com.repository.AirlineRepository;
import com.repository.CityRepository;
import com.repository.FlightRepository;

@Service
public class AdminService {
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	AirlineRepository airlineRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	FlightRepository flightRepository;
	
	public int adminLogin(Admin admin) {
		Optional<Admin> result = adminRepository.findById(admin.getEmail());
		
		if (result.isEmpty()) {
			return 0;
		}
		
		Admin user = result.get();
		if (user.getPassword().equals(admin.getPassword())) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
	
	public int changePassword(String email, String currentPassword, String newPassword) {
		Optional<Admin> result = adminRepository.findById(email);
		
		if (result.isEmpty()) {
			return 0;
		}
		
		Admin admin = result.get();
		if(admin.getPassword().equals(currentPassword)) {
			admin.setPassword(newPassword);
			adminRepository.save(admin);
			return 1;
		}
		
		else {
			return -1;
		}
	}
	
	
	public int addAirline	(Airline airline) {
		Optional<Airline> result = airlineRepository.findById(airline.getAirline());
		
		if (result.isPresent()) {
			return 0;
		}
		else {
			airlineRepository.save(airline);
			return 1;
		}
	}
	
	
	public int deleteAirline(Airline airline) {
		Optional<Airline> result = airlineRepository.findById(airline.getAirline());
		
		if (result.isEmpty()) {
			return 0;
		}
		else {
			airlineRepository.deleteById(airline.getAirline());;
			return 1;
		}
	}
	
	
	public List<Airline> getAllAirline() {
		List<Airline> allAirline = airlineRepository.findAll();
		return allAirline;
	}
	
	
	public int addCity(City city) {
		Optional<City> result = cityRepository.findById(city.getCity());
		
		if (result.isPresent()) {
			return 0;
		}
		else {
			cityRepository.save(city);
			return 1;
		}
	}
	
	
	public int deleteCity(City city) {
		Optional<City> result = cityRepository.findById(city.getCity());
		
		if (result.isEmpty()) {
			return 0;
		}
		else {
			cityRepository.deleteById(city.getCity());;
			return 1;
		}
	}
	
	
	public List<City> getAllCity() {
		List<City> allCity = cityRepository.findAll();
		return allCity;
	}
	
	
	public int addFlight(Flight flight) {
		Optional<Flight> result = flightRepository.findById(flight.getFlightId());
		
		if (result.isPresent()) {
			return 0;
		}
		else if(flight.getPrice() <= 0) {
			return -1;
		}
		else if (flight.getSourceCity().getCity().equals(flight.getDestinationCity().getCity())) {
			return -2;
		}
		else {
			flightRepository.save(flight);
			return 1;
		}
	}
	
	
	public int deleteFlight(String flightId) {
		Optional<Flight> result = flightRepository.findById(flightId);
		
		if (result.isEmpty()) {
			return 0;
		}
		else {
			flightRepository.deleteById(flightId);;
			return 1;
		}
	}
	
	
	public List<Flight> getAllFlight() {
		List<Flight> allFlight = flightRepository.findAll();
		return allFlight;
	}
	
	
	
	
	
}
