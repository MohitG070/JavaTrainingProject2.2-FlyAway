package com.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class City {
	
	@Id
	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
