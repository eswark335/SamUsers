package com.sampana.users.dto;

import com.sampana.users.entity.Address;
import com.sampana.users.entity.User;

public class UserDto {

	private User user;
	private Address address;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
