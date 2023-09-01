package com.osttra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osttra.repository.UserRepository;
import com.osttra.to.Person;
import com.osttra.to.Student;
import com.osttra.to.Teacher;

@Service
public class UserService {
	
		@Autowired
		UserRepository userRepository;
		
		
		public UserService ()
		{
			System.out.println("inside UserService");
		}
		
		
		public void addUser(Person person)
		{
			if(!person.getRole().equalsIgnoreCase("Admin"))
			{
				person.setStatus("Block");
			}
			else
			{
				person.setStatus("Active");
			}
			userRepository.setUser(person);
			
			System.out.println("inside addUser service");
		}
		
		public Person getUser(String email, String password, String role)
		{
			Person person = userRepository.getUserByCredentials(email,password,role);
			return person;
		}		
		
		public List<Person> getAllStudent()
		{
			List<Person> persons = userRepository.getAllUserByRole("student");
			return persons;
		}
		public List<Person> getAllTeacher()
		{
			List<Person> persons = userRepository.getAllUserByRole("teacher");
			return persons;
		}
		
		public void updateUser(Person personOld, Person personNew)
		{
			userRepository.updateSingleUser(personOld, personNew);
		}
		public void updateUserStatus(String email, String  status)
		{
			if(status.equalsIgnoreCase("Activate"))
			{
				userRepository.toggleUserStatus(email, "Active");
				userRepository.resetMissMatch(email);
			}
			else
			{
				userRepository.toggleUserStatus(email, "Block");
			}
			
		}
		public void deleteUserByEmail(String email)
		{
			userRepository.deleteUser(email);
		}
}
