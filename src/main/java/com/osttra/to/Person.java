package com.osttra.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Person {
	String name;
	String email;
	String password;
	String className;
	String address;
	String status;
	int credMissMatch;
	String role;
}
