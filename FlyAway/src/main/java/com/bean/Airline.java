package com.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Airline {

	@Id
	private String airline;

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}
}
