package com.osttra.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student extends Person{
	
	public Student(String objectName, String objectEmail, String objectPassword, String objectAddress, String role,
			String objectClass, String objectStatus) {
		this.name = objectName;
		this.email = objectEmail;
		this.password = objectPassword;
		this.address = objectAddress;
		this.role = role;
		this.className = objectClass;
		this.status =objectStatus;
		// TODO Auto-generated constructor stub
	}

	String status;
}
