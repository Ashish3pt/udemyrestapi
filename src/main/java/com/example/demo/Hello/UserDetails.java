package com.example.demo.Hello;

public class UserDetails {
	private String filename;
	private String lastname;
	private String city;
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public UserDetails(String filename, String lastname, String city) {
		super();
		this.filename = filename;
		this.lastname = lastname;
		this.city = city;
	}
	@Override
	public String toString() {
		return "UserDetails [filename=" + filename + ", lastname=" + lastname + ", city=" + city + "]";
	}
	
	

}
