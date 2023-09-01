<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.osttra.to.Person, java.util.List" %>
<html lang="en"><head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">
    <title>Cover Template · Bootstrap v5.0</title>
    <link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
  </head>
  <body class="d-flex h-100 text-center ">
    
    <%
    	Person person = (Person) session.getAttribute("person");
    	List<Person> persons = (List<Person>) request.getAttribute("persons");
    %>
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
  <header class="mb-3">
    <div>
      <h3 class="float-md-start mb-0">EdTech Labs</h3>
      <nav class="nav nav-masthead justify-content-center float-md-end">
        <a class="nav-link active" aria-current="page" href="/">Home</a>
        <a class="nav-link" href="updateUser">update</a>
      </nav>
    </div>
  </header>
	
 <h2 style=" color: #6f6f6f">Your Profile</h2>
 
  <table class="table" style="margin-left:100px;">
  <thead>
    <tr>
      <th scope="col">Name</th>
      <th scope="col">Email</th>
      <th scope="col">Class</th>
      <th scope="col">Address</th>
      <th scope="col">Role</th>
      <th scope="col">Update</th>
    </tr>
  </thead>
  <tbody>
  
 
    <tr>
      <td>${person.getName()}</td>
      <td>${person.getEmail()}</td>
      <td>${person.getPassword()}</td>
      <td>${person.getAddress()}</td>
      <td>${person.getRole()}</td>
      <td><a href = "/updateUser">Update</a></td>
    </tr>
  </tbody>
</table>	


	<%
		if(persons != null) {
	%>
  <h2 style=" color: #6f6f6f">Application Users</h2>
  <table class="table" style="margin-left:100px;"  >
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Name</th>
      <th scope="col">Email</th>
      <th scope="col">Class</th>
      <th scope="col">Address</th>
      <th scope="col">Role</th>
      <th scope="col">Status</th>
       <% 
		  if(person.getRole().equalsIgnoreCase("Admin"))
		  {
		%>
	  <th scope="col">Delete</th>    
	  <%} %>
    </tr>
  </thead>
  <tbody> 
  <%} %>
  <% 

  
  if(persons != null && persons.size() > 1)
  {
  		for(int i=0;i<persons.size();i++)
  		{
  %>
  
		    <tr>
		      <th scope="row"><%= i+1%></th>
		      <td><%= persons.get(i).getName()%></td>
		      <td><%= persons.get(i).getEmail() %></td>
		      <td><%= persons.get(i).getClassName() %></td>
		      <td><%= persons.get(i).getAddress() %></td>
		      <td><%= persons.get(i).getRole() %></td>
		      <td class="toggle">
		      <% 
		      	if(person.getRole().equalsIgnoreCase("Admin") && persons.get(i).getStatus().equalsIgnoreCase("Block"))
		      	{
		      %>
		      
		      <form style="margin:0px;" method="post" action="/allowUser/<%=persons.get(i).getEmail()%>">
              	<button type="submit" class="btn btn-success btn-s " onClick="refreshPage()" value ="Activate"  name ="status">Activate</button>
              </form>
              
              <%}
		      	else if(person.getRole().equalsIgnoreCase("Admin") && persons.get(i).getStatus().equalsIgnoreCase("Active")) 
		      	{
		      	%>
		      <form style="margin:0px;" method="post" action="/allowUser/<%=persons.get(i).getEmail()%>">
              	<button type="submit" class="btn btn-danger btn-s " onClick="refreshPage()" value ="Block" name ="status">Block</button>
              </form>
              
		      <%}else{ %>
		      	<%= persons.get(i).getStatus() %>
		      <%} %>
		      </td>
		      <% 
		      	if(person.getRole().equalsIgnoreCase("Admin"))
		      	{
		      %>
		      <td>
		      <form style="margin:0px;" method="post" action="/deleteUser/<%=persons.get(i).getEmail()%>">
              	<button type="submit" class="btn btn-danger btn-s " onClick="refreshPage()" name ="delete">Delete</button>
              </form>
              </td>
		      <%} %>
		    </tr>
  <%
    	}
  }	
  %>
  </tbody>
</table>

  <footer class="mt-auto text-white-50">
    <p>Cover template for <a href="https://getbootstrap.com/" class="text-white">Bootstrap</a>, by <a href="https://twitter.com/mdo" class="text-white">@mdo</a>.</p>
  </footer>
</div>

</body>
<script>
function refreshPage(){
    window.location.reload();
} 
</script>
  </html>