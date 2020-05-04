package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserAddressInfo {

	@Column(name = "address")
	private String address;

	@Column(name = "zip_code")
	private String zipCodeX;

	public String getZipCodeX() {
		return zipCodeX;
	}

	public void setZipCodeX(String zipCodeX) {
		this.zipCodeX = zipCodeX;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
