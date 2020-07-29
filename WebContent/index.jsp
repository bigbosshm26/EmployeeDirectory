<html>
<head>
	<title></title>	
</head>
<link href ="https://unpkg.com/bootstrap@4.3.1/dist/css/bootstrap.min.css" rel= "stylesheet" />

<body>
	<%
		String email = (String)session.getAttribute("email");
		//if user is already logged in, redirect it to list employees
		if(email != null){
			response.sendRedirect("EmployeeController?action=LIST");
		}
		
		String status = request.getParameter("status");
		if(status != null){
			if(status.equals("false")){
				out.print("Bad credentials");
			}else if(status.equals("error")){
				out.print("Some errors occured");
			}
		}
	%>
	<div class="container">
	<form action="${pageContext.request.contextPath}/LoginController" method="POST">
		<div class="card">
					
			<div class="card-header">
				Login
			</div>
			<div class="card-body">
				Email: <input type="text" name="email" class="form-control" placeholder="Enter email"/><br/>		
				Password: <input type="password" name="password" class="form-control" placeholder="Enter password"/><br/>								
			</div>
			<div class="card-footer">
				<input type="submit" value="login" class="btn btn-primary" /><br/>											
			</div>
			
		</div>
	</form>
	</div>
</body>

</html>