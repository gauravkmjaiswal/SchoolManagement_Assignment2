<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<style>
	@media (min-width: 1025px) {
		.h-custom {
		height: 100% !important;
		}
	}
</style>

</head>
<body>
<section class="h-1000 h-custom" style="background-color: #8fc4b7;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-lg-8 col-xl-6">
        <div class="card rounded-3">
          <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/img3.webp"
            class="w-100" style="border-top-left-radius: .3rem; border-top-right-radius: .3rem;"
            alt="Sample photo">
          <div class="card-body p-4 p-md-5">
            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 px-md-2">Login Info</h3>
			${ errorMessage }
            <form class="px-md-2" method="post" action="loginUser">

              <div class="form-outline mb-4">
                
                <label class="form-label" for="form3Example1q">Email</label>
                <input type="text" id="form3Example1q" name="email" class="form-control" />
                
              </div>
             
              <div class="form-outline mb-4">
                <label class="form-label" for="form3Example2q">Password</label>
                <input type="text" id="form3Example2q" name="password" class="form-control" />
                
              </div>
              
              <div class="mb-4">
			      <label for="disabledSelect" class="form-label">User Type</label>
			      <select id="disabledSelect" class="form-select" name ="role" onchange="manipulateForm(this.value)">
			        <option>Admin</option>
			        <option>Student</option>
			        <option>Teacher</option>
			      </select>

              </div>

              
				
              <button type="submit" class="btn btn-success btn-lg mb-1">Submit</button>

            </form>

          </div>
        </div>
      </div>
    </div>
  </div>
</section>
</body>
</html>