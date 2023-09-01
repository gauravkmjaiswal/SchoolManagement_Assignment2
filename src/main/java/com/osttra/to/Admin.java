package com.osttra.to;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Admin extends Person{public Admin(String name, String email, String password, String address) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
	}
}
