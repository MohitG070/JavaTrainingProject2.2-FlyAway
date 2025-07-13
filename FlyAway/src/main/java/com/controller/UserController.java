package com.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bean.BookingSessionData;
import com.bean.City;
import com.bean.Flight;
import com.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;

	@GetMapping("/searchFlight")
	public String showUserSearchFlight(Model mm) {
		List<City> allCity = userService.getAllCity();
		mm.addAttribute("city", allCity);
		return "userSearchFlight";
	}
	
	@PostMapping("/registerDetails")
	public String showUserDetailsRegister(@RequestParam String flightId, HttpSession session) {
		BookingSessionData bookingData = (BookingSessionData) session.getAttribute("bookingData");
		Flight flight = userService.searchFlight(flightId);
		bookingData.setSelectedFlight(flight);
		session.setAttribute("bookingData", bookingData);
		return "userRegisterDetails";
	}
	
	@PostMapping("/searchFlight")
	public String userSearchFlight(Model mm, @RequestParam City sourceCity,
									@RequestParam City destinationCity,
									@RequestParam LocalDate dateOfTravel,
									@RequestParam int numberOfPassengers,
									HttpSession session) {
		
		if (sourceCity.getCity().equals(destinationCity.getCity())){
			mm.addAttribute("error", "Source and Destination must be different");
			List<City> allCity = userService.getAllCity();
			mm.addAttribute("city", allCity);
			return "userSearchFlight";
		}
		else if (numberOfPassengers < 1) {
			mm.addAttribute("error", "Number of Passengers must be positive");
			List<City> allCity = userService.getAllCity();
			mm.addAttribute("city", allCity);
			return "userSearchFlight";
		}
		
		BookingSessionData bookingData = new BookingSessionData();
		bookingData.setSourceCity(sourceCity);
		bookingData.setDestinationCity(destinationCity);
		bookingData.setDateOfTravel(dateOfTravel);
		bookingData.setNumberOfPassengers(numberOfPassengers);
		
		session.setAttribute("bookingData", bookingData);
		
		List<Flight> listOfFlight = userService.searchFlight(sourceCity, destinationCity, dateOfTravel, numberOfPassengers);
		
		if (listOfFlight.size() == 0) {
			mm.addAttribute("error", "No Flights Found");
		}
		mm.addAttribute("numberOfPassengers", numberOfPassengers);
		mm.addAttribute("flight", listOfFlight);
		
		return "userFlight";
	}
	
	
	@PostMapping("/bookingReview")
	public String userDetailsRegister(Model mm, @RequestParam String name,
										@RequestParam String email, @RequestParam String contactNumber,
										HttpSession session) {
		
		BookingSessionData bookingData = (BookingSessionData) session.getAttribute("bookingData");
		bookingData.setName(name);
		bookingData.setEmail(email);
		bookingData.setContactNumber(contactNumber);
		
		session.setAttribute("bookingData", bookingData);
		mm.addAttribute("bookingData", bookingData);
		return "userBookingReview";
	}
	
	@GetMapping("/paymentDetails")
	public String userPaymentDetails(Model mm) {
		return "userPaymentDetails";
	}

	@GetMapping("/confirmBooking")
	public String userConfirmBooking(Model mm) {
		return "userConfirmBooking";
	}
}
