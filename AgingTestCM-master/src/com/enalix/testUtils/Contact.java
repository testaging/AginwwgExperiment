package com.enalix.testUtils;

public class Contact {
	
	private String name;
	private String phone;
	private String email;
	private String address;
	public Contact() {
	}
	public Contact(String _name, String _phone, String _email, String _address) {
		this.setName(_name);
		this.setPhone(_phone);
		this.setEmali(_email);
		this.setAddress(_address);
	}
	public void setName(String _name) {
		this.name = _name;
	}
	public String getName() {
		return this.name;
	}
	public void setPhone(String _phone) {
		this.phone = _phone;
	}
	public String getPhone() {
		return this.phone;
	}
	public void setEmali(String _email) {
		
		this.email = _email;
	}
	public String getEmail() {
		return this.email;
	}
	public void setAddress(String _address) {
		this.address = _address;
	}
	public String getAddress() {
		return this.address;
	}
	public String toString() {
		return this.name + "---" + this.phone + "---" + this.email + "---" + this.address;
	}
}
