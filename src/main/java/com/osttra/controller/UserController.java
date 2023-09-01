package com.osttra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import com.osttra.service.UserService;
import com.osttra.to.Person;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String home()
	{
		return "index";
	}
	
	@GetMapping("/signup")
	public String signUp()
	{
		return "registerUser";
	}
	
	@GetMapping("/login")
	public String signIn()
	{
		return "loginUser";
	}
	
	@PostMapping("/registerUser")
	public String registerUser(Person person)
	{
		System.out.println(person);
		userService.addUser(person);
		return "loginUser";
	}
	
	public List<Person> userList(Person person)
	{
		System.out.println("getting user list");
		List<Person> persons = null;
		if(!person.getRole().equals("Student"))
		{
			persons = userService.getAllStudent();
		}
		if(person.getRole().equals("Admin"))
		{
	
			if(persons != null)
			{
				persons.addAll(userService.getAllTeacher());
			}
			else
			{
				persons = userService.getAllTeacher();
			}	
		}
		return persons;
	}
	
	
	@PostMapping("/loginUser")
	public ModelAndView loginUser(String email, String password, String role ,HttpServletRequest request)
	{
		ModelAndView modelAndView = null;
		Person person =userService.getUser(email,password,role);
		
		if(person!=null)
		{
			
			if(person.getCredMissMatch()>2 || person.getStatus().equalsIgnoreCase("Block"))
			{
				modelAndView = new ModelAndView("loginUser");
				modelAndView.addObject("errorMessage", "Please Contact Admin Department, and try again!!");
			}
			else
			{
				HttpSession session = request.getSession();
				modelAndView = new ModelAndView("welcomeUser");
				
				List<Person> persons = userList(person);
				
				modelAndView.addObject("persons", persons);
				session.setAttribute("person",person);
			}
			
			
		}
		else
		{
			modelAndView = new ModelAndView("loginUser");
			modelAndView.addObject("errorMessage", " Wrong Credentials, please try again!!");
		}
		return modelAndView;
	}
	
	@GetMapping("/updateUser")
	public ModelAndView updateUser(HttpServletRequest request)
	{
		
		ModelAndView modelAndView = null;
		HttpSession session =request.getSession(false);

		if(session != null)
		{
			modelAndView = new ModelAndView("updateUser");
		}
		else 
		{
			modelAndView = new ModelAndView("loginUser");
			modelAndView.addObject("errorMessage", " Session End, please login first!!");
		}
		
		return modelAndView;
	}
	
	@PostMapping("/updateUserDetails")
	public ModelAndView updateUserDetails(Person person ,HttpServletRequest request)
	{
		
		ModelAndView modelAndView = null;
		HttpSession session =request.getSession(false);
		
		if(session != null)
		{
			Person personOld = (Person) session.getAttribute("person");
			userService.updateUser(personOld,person);
			
			person.setClassName(personOld.getClassName());
			person.setCredMissMatch(personOld.getCredMissMatch());
			person.setRole(personOld.getRole());
			person.setStatus(personOld.getRole());
			
			List<Person> persons = userList(person);

			session.setAttribute("person",person);
			
			modelAndView = new ModelAndView("welcomeUser");
			modelAndView.addObject("persons", persons);
		
			
			
			
		}
		else 
		{
			modelAndView = new ModelAndView("loginUser");
			modelAndView.addObject("errorMessage", " Session End, please login first!!");
		}
		
		return modelAndView;
		
	}
	
	@PostMapping("/allowUser/{email}")
	public ModelAndView allowUser(@PathVariable String email ,String status, HttpServletRequest request)
	{
		System.out.println(email + " , "+ status);
		ModelAndView modelAndView = null;
		HttpSession session = request.getSession(false);
		if(session != null)
		{
			Person person = (Person) session.getAttribute("person");
			
			userService.updateUserStatus(email,status);
			List<Person> persons = userList(person);
			
			if(persons != null)
			{
				if(person.getRole().equalsIgnoreCase("Admin"))
				{
					
					modelAndView = new ModelAndView("welcomeUser");
					modelAndView.addObject("persons", persons);
				}
			}
			else
			{
				modelAndView = new ModelAndView("loginUser");
				modelAndView.addObject("errorMessage", "Please login again");
			}

		}
		else 
		{
			modelAndView = new ModelAndView("loginUser");
			modelAndView.addObject("errorMessage", "Please sign in with Admin Account");
		}
		return modelAndView;
	}
	
	@PostMapping("/deleteUser/{email}")
	public ModelAndView deleteUser(@PathVariable String email ,String status, HttpServletRequest request)
	{
		ModelAndView modelAndView = null;
		HttpSession session = request.getSession(false);
		if(session != null)
		{
			Person person = (Person) session.getAttribute("person");
			
			userService.deleteUserByEmail(email);
			List<Person> persons = userList(person);
			
			if(persons != null)
			{
				if(person.getRole().equalsIgnoreCase("Admin"))
				{
					
					modelAndView = new ModelAndView("welcomeUser");
					modelAndView.addObject("persons", persons);
				}
			}
			else
			{
				modelAndView = new ModelAndView("loginUser");
				modelAndView.addObject("errorMessage", "Please login again");
			}
			

		}
		else 
		{
			modelAndView = new ModelAndView("loginUser");
			modelAndView.addObject("errorMessage", "Please sign in with Admin Account");
		}
		return modelAndView;
	}
	
}
