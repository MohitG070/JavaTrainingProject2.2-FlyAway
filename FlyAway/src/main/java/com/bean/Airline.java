package com.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Airline {

	@Id
	private String airliine;

	public String getAirliine() {
		return airliine;
	}

	public void setAirliine(String airliine) {
		this.airliine = airliine;
	}
}
