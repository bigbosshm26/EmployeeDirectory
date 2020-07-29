<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<link href ="https://unpkg.com/bootstrap@4.3.1/dist/css/bootstrap.min.css" rel= "stylesheet" />
<body>
	<%
		String email = (String)session.getAttribute("email");
		if(email == null){
			response.sendRedirect("index.jsp");
		}
			
	%>

	<div class="container">
		<div>
			<a href="${pageContext.request.contextPath}/logout.jsp">Logout</a>
		</div>
		<p>${message}</p>
		<button class="btn btn-primary" onclick="window.location.href='views/employee-add.jsp'">Add Employee</button>
		<table border ="1" class ="table table-stripped table-bordered table-hover">
		<tr class ="thead-dark">
			<th>Name</th>
			<th>Department</th>
			<th>Date of birth</th>
			<th>Action</th>
		</tr>
		<c:forEach items = "${list}" var = "employee">
			<tr>
				<td>${employee.name}</td>
				<td>${employee.department}</td>
				<td>${employee.dob}</td>
				<td>
					<a href="${pageContext.request.contextPath}/EmployeeController?action=EDIT&id=${employee.id}">Edit</a>
					|
					<a href="${pageContext.request.contextPath}/EmployeeController?action=DELETE&id=${employee.id}">Delete</a>
					
				</td>
			</tr>
		</c:forEach>
	</table>
	</div>

</body>
</html>