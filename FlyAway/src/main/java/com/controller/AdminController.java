package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bean.Admin;
import com.bean.Airline;
import com.bean.City;
import com.bean.Flight;
import com.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@GetMapping("/login")
	public String showAdminLoginPage(Model mm) {
		return "adminLogin";
	}
	
	@GetMapping("/changePassword")
	public String showAdminChangePassword(Model mm) {
		return "adminChangePassword";
	}
	
	@GetMapping("/dashboard")
	public String showAdminDashboard(Model mm) {
		return "adminDashboard";
	}
	
	@GetMapping("/addAirline")
	public String showAdminAddAirline(Model mm) {
		return "adminAddAirline";
	}
	
	@GetMapping("/deleteAirline")
	public String showAdminDeleteAirline(Model mm) {
		return "adminDeleteAirline";
	}
	
	@GetMapping("/addCity")
	public String showAdminAddCity(Model mm) {
		return "adminAddCity";
	}
	
	@GetMapping("/deleteCity")
	public String showAdminDeleteCity(Model mm) {
		return "adminDeleteCity";
	}

	@GetMapping("/addFlight")
	public String showAdminAddFlight(Model mm) {
		List<City> allCity = adminService.getAllCity();
		mm.addAttribute("city", allCity);
		List<Airline> allAirline = adminService.getAllAirline();
		mm.addAttribute("airline", allAirline);
		return "adminAddFlight";
	}
	
	@GetMapping("/deleteFlight")
	public String showAdminDeleteFlight(Model mm) {
		return "adminDeleteFlight";
	}
	
	@PostMapping("/login")
	public String adminLogin(Model mm, @ModelAttribute Admin admin) {
		
		int result = adminService.adminLogin(admin);
		if (result==1) {
			return "redirect:/admin/dashboard";
		}
		else if(result==0) {
			mm.addAttribute("error", "Account doesn't existes");
		}
		else {
			mm.addAttribute("error", "Incorrect Password");
		}
		
		return "adminLogin";
	}
	
	
	@PostMapping("/changePassword")
	public String adminChangePassword(Model mm, @RequestParam String email, @RequestParam String currentPassword, @RequestParam String newPassword) {
		
		int result = adminService.changePassword(email, currentPassword, newPassword);
		if (result == 0) {
			mm.addAttribute("error", "Admin doesn't exists");
		}
		else if (result == 1) {
			mm.addAttribute("msg", "Password changed successfully");
		}
		else {
			mm.addAttribute("error", "Please Enter the correct Current Password");
		}
		
		return "adminChangePassword";
	}
	
	
	@PostMapping("/addAirline")
	public String adminAddAirline(Model mm, @ModelAttribute Airline airline) {
		int result = adminService.addAirline(airline);
		
		if (result == 0) {
			mm.addAttribute("error", "Airline already exists");
		}
		else {
			mm.addAttribute("msg", "Airline saved successfully");
		}
		
		return "adminAddAirline";
	}
	
	
	@PostMapping("/deleteAirline")
	public String adminDeleteAirline(Model mm, @ModelAttribute Airline airline) {
		int result = adminService.deleteAirline(airline);
		
		if (result == 0) {
			mm.addAttribute("error", "Airline isn't present");
		}
		else {
			mm.addAttribute("msg", "Airline deleted successfully");
		}
		
		return "adminDeleteAirline";
	}
	
	
	@GetMapping("/allAirline")
	public String adminAllAirline(Model mm) {
		List<Airline> allAirline = adminService.getAllAirline();
		
		mm.addAttribute("airline", allAirline);			
		if (allAirline.isEmpty()) {
			mm.addAttribute("error", "No airlines found");			
		}

		return "adminGetAllAirline";
	}
	
	
	@PostMapping("/addCity")
	public String adminAddCity(Model mm, @ModelAttribute City city) {
		int result = adminService.addCity(city);
		
		if (result == 0) {
			mm.addAttribute("error", "City already exists");
		}
		else {
			mm.addAttribute("msg", "City saved successfully");
		}
		
		return "adminAddCity";
	}
	
	
	@PostMapping("/deleteCity")
	public String adminDeleteCity(Model mm, @ModelAttribute City city) {
		int result = adminService.deleteCity(city);
		
		if (result == 0) {
			mm.addAttribute("error", "City isn't present");
		}
		else {
			mm.addAttribute("msg", "City deleted successfully");
		}
		
		return "adminDeleteCity";
	}
	
	
	@GetMapping("/allCity")
	public String adminAllCity(Model mm) {
		List<City> allCity = adminService.getAllCity();
		
		mm.addAttribute("city", allCity);			
		if (allCity.isEmpty()) {
			mm.addAttribute("error", "No cities found");			
		}
		
		return "adminGetAllCity";
	}
	
	
	@PostMapping("/addFlight")
	public String adminAddFlight(Model mm, @ModelAttribute Flight flight) {
		int result = adminService.addFlight(flight);
		
		if (result == 0) {
			mm.addAttribute("error", "Duplicate Flight ID");
		}
		else if (result == -1) {
			mm.addAttribute("error", "Price must be positive");
		}
		else if (result == -2) {
			mm.addAttribute("error", "Source and Destination must be different");
		}
		else {
			mm.addAttribute("msg", "Flight added successfully");
		}
		
		List<City> allCity = adminService.getAllCity();
		mm.addAttribute("city", allCity);
		List<Airline> allAirline = adminService.getAllAirline();
		mm.addAttribute("airline", allAirline);
		return "adminAddFlight";
	}
	
	
	@PostMapping("/deleteFlight")
	public String adminDeleteFlight(Model mm, @RequestParam String flightId) {
		int result = adminService.deleteFlight(flightId);
		
		if (result == 0) {
			mm.addAttribute("error", "Flight isn't present");
		}
		else {
			mm.addAttribute("msg", "Flight deleted successfully");
		}
		
		return "adminDeleteFlight";
	}
	
	
	@GetMapping("/allFlight")
	public String adminAllFlight(Model mm) {
		List<Flight> allFlight = adminService.getAllFlight();
		
		mm.addAttribute("flight", allFlight);			
		if (allFlight.isEmpty()) {
			mm.addAttribute("error", "No flights found");			
		}
		
		return "adminGetAllFlight";
	}
	
	
}
