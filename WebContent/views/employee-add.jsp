<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Employee</title>
</head>
<link href ="https://unpkg.com/bootstrap@4.3.1/dist/css/bootstrap.min.css" rel= "stylesheet" />
<body>
	<div class="container">
		<h1>Employee Directory</h1>
		<hr/>
		<div class="row">
		<div class="col-md-4">
		<div>
			<a href="${pageContext.request.contextPath}/logout.jsp">Logout</a>
		</div>
		<form action="${pageContext.request.contextPath}/EmployeeController" method="POST">
		<div class="form-group">
				<input type="text" name="name" placeholder = "Enter name" value="${employee.name}" class="form-control"/><br/>
		</div>
		<div class="form-group">
				<input type="date" name="date of birth" value="${employee.dob}" class="form-control"/><br/>
		</div>
		<div class="form-group">
				<input type="text" name = "department" placeholder = "Enter department" value="${employee.department}" class="form-control"/><br/>
		</div>
				<input type="hidden" name = "id"value="${employee.id}" /><br/>
	 	
				<button class="btn btn-primary" type="submit">Save employee</button>
		</form>
		</div>
		</div>
	</div>
</body>
</html>