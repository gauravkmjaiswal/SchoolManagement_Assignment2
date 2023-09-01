package com.osttra.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends Person{
	public Teacher(String objectName, String objectEmail, String objectPassword, String objectAddress, String role,
			String objectClass, String objectStatus) {
		this.name = objectName;
		this.email = objectEmail;
		this.password = objectPassword;
		this.address = objectAddress;
		this.role = role;
		this.className = objectClass;
		this.status =objectStatus;
	}

	String status;
	
}
