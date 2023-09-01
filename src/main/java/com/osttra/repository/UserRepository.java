package com.osttra.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.osttra.DB.DbUtils;
import com.osttra.to.Admin;
import com.osttra.to.Person;
import com.osttra.to.Student;
import com.osttra.to.Teacher;

@Repository
public class UserRepository {

	 Connection connection = null;
	 Person person = null;
	 ResultSet resultSet = null;
	 
	 public UserRepository()
	 {
		 connection = DbUtils.makeConnection();
		 System.out.println("inside user Repo");
	 }
	 
	
	 public void setUser(Person person)
	 {
		 try {
				PreparedStatement statement = connection.prepareStatement("insert into app_user values(?,?,?,?,?,?,?,?)");
				statement.setString(1 , person.getName());
				statement.setString(2 , person.getEmail());
				statement.setString(3 , person.getPassword());
				statement.setString(4 , person.getClassName());
				statement.setString(5 , person.getAddress());
				statement.setString(6 , person.getStatus());
				statement.setInt(7 , person.getCredMissMatch());
				statement.setString(8 , person.getRole());
				statement.executeUpdate();
			}
			catch(Exception e)
			{
				System.out.println("inside userRepo.addUser.\nError : " +e);
			}
	 }
	 
	 public Person getUserByCredentials(String email ,String password ,String role)
	 {
		 String objectName = null ;
		 String objectEmail = null;
		 String objectPassword = null;
		 String objectAddress= null;
		 String objectClass = null;
		 String objectStatus = null;
		 int objectCredMissMatch = 0;
		 String objectRole = null;
		 Person person = null;
		 
		 
		 try {
			    PreparedStatement statement = connection.prepareStatement("select * from app_user where email = ? and password = ? and role = ?");
				statement.setString(1,email);
				statement.setString(2,password);
				statement.setString(3,role);
				resultSet = statement.executeQuery();
				
				
				if(resultSet.next())
				{
					System.out.println("inside next()");
					objectName = resultSet.getString(1);
					objectEmail = resultSet.getString(2);
					objectPassword = resultSet.getString(3);
					objectClass = resultSet.getString(4);
					objectAddress = resultSet.getString(5);
					objectStatus = resultSet.getString(6);
					objectCredMissMatch = resultSet.getInt(7);
					objectRole = resultSet.getString(8);
					person = new Person(objectName,objectEmail,objectPassword,objectClass,objectAddress,objectStatus,objectCredMissMatch,objectRole);
				}
				else
				{
					statement = connection.prepareStatement("select * from app_user where email = ? ");
					statement.setString(1,email);
					resultSet = statement.executeQuery();
					objectCredMissMatch = resultSet.getInt(7);
					
					if(resultSet.next())
					{
						if(objectCredMissMatch >2 )
						{
							statement = connection.prepareStatement("update app_user set cred_miss_match = cred_miss_match+1 , status = ? where email = ?");
							statement.setString(1,"Block");
							statement.setString(2,email);
						}
						else
						{
							statement = connection.prepareStatement("update app_user set cred_miss_match = cred_miss_match+1 where email = ?");
							statement.setString(1,email);
						}
						
						statement.executeUpdate();
						
						
					}
				}
				
			}
			catch(Exception e)
			{
				System.out.println("inside userRepo.getUser .\nError : " +e);
			}
		 return person;
	 }
	 
	 public List<Person> getAllUserByRole(String role)
	 {
		 
		 String objectName = null ;
		 String objectEmail = null;
		 String objectPassword = null;
		 String objectAddress= null;
		 String objectClass = null;
		 String objectStatus = null;
		 int objectCredMissMatch = 0;
		 String objectRole = null;
		 Person person = null;
		 List<Person> persons = new ArrayList<Person>();
		 
		 try {
			    PreparedStatement statement = connection.prepareStatement("select * from app_user where role = ?");
			    statement.setString(1,role);
				resultSet = statement.executeQuery();
				
				while(resultSet.next())
				{
					objectName = resultSet.getString(1);
					objectEmail = resultSet.getString(2);
					objectPassword = resultSet.getString(3);
					objectClass = resultSet.getString(4);
					objectAddress = resultSet.getString(5);
					objectStatus = resultSet.getString(6);
					objectCredMissMatch = resultSet.getInt(7);
					objectRole = resultSet.getString(8);
					person = new Person(objectName,objectEmail,objectPassword,objectClass,objectAddress,objectStatus,objectCredMissMatch,objectRole);
					persons.add(person);
				}
				
			}
			catch(Exception e)
			{
				System.out.println("inside userRepo.getAllUserByRole .\nError : " +e);
			}
		 return persons;
	 }
	 
	 public void updateSingleUser(Person personOld,Person personNew)
	 {
		 try {
				PreparedStatement statement = connection.prepareStatement("update app_user set name = ?, email = ?, password = ?, address = ? where email = ? and password = ? ");
				statement.setString(1 , personNew.getName());
				statement.setString(2 , personNew.getEmail());
				statement.setString(3 , personNew.getPassword());
				statement.setString(4 , personNew.getAddress());
				statement.setString(5 , personOld.getEmail());
				statement.setString(6 , personOld.getPassword());
		
				statement.executeUpdate();
			}
			catch(Exception e)
			{
				System.out.println("inside userRepo.updateSingleUser .\nError : " +e);
			}
	 }
	 
	 public void toggleUserStatus(String email, String status)
	 {
		 try {
				PreparedStatement statement = connection.prepareStatement("update app_user set status = ? where email = ? ");
				statement.setString(1 , status);
				statement.setString(2 , email);
				statement.executeUpdate();
			}
			catch(Exception e)
			{
				System.out.println("inside userRepo.updateSingleUser .\nError : " +e);
			}
	 }
	 
	 public void resetMissMatch(String email)
	 {
		 try {
				PreparedStatement statement = connection.prepareStatement("update app_user set cred_miss_match = ? where email = ? ");
				statement.setInt(1 , 0);
				statement.setString(2 , email);
				statement.executeUpdate();
			}
			catch(Exception e)
			{
				System.out.println("inside userRepo.updateSingleUser .\nError : " +e);
			}
	 }
	 public void deleteUser(String email)
	 {
		 try {
				PreparedStatement statement = connection.prepareStatement("delete from app_user where email = ? ");

				statement.setString(1 , email);
				statement.executeUpdate();
			}
			catch(Exception e)
			{
				System.out.println("inside userRepo.deleteUser .\nError : " +e);
			}
	 }
	 
}
