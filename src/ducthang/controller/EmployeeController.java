package ducthang.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ducthang.dao.EmployeeDAO;
import ducthang.dao.EmployeeDAOImpl;
import ducthang.entity.Employee;


public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	RequestDispatcher dispatcher = null;
	
	EmployeeDAO employeeDAO =null;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		employeeDAO = new EmployeeDAOImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action == null) {
			action = "LIST";
		}
	
		
		switch(action) {
			
			case "LIST":
				listEmployees(request, response);
				break;
			case "EDIT":
				getSingleEmployee(request, response);
				break;
			case "DELETE":
				deleteEmployee(request, response);
				break;
			default:
				listEmployees(request, response);
				break;
				
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String dob = request.getParameter("date of birth");
		String department = request.getParameter("department");

		
		Employee employee = new Employee();
		employee.setName(name);
		employee.setDob(dob);
		employee.setDepartment(department);
		// call DAO method
		if(id==null || id.isEmpty()) {
			if(employeeDAO.addEmployee(employee)) {
			request.setAttribute("message", "saved succesfully");
			}
		}else {
			employee.setId(Integer.parseInt(id));
			if(employeeDAO.updateEmployee(employee)) {
			request.setAttribute("message", "updated succesfully");	
			}
		}
		listEmployees(request, response);
	
	
			
	}
	public void listEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Employee> list;	
		// call DAO method
		list = employeeDAO.get();
		// add the employees to request object
		request.setAttribute("list", list);
		//get the request dispatcher
		dispatcher = request.getRequestDispatcher("/views/employee-list.jsp");
		// forward the req and res object
		dispatcher.forward(request, response);
	}

	public void getSingleEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		Employee employee = employeeDAO.readSingleEmployee(Integer.parseInt(id));
		
		request.setAttribute("employee", employee);
		
		dispatcher = request.getRequestDispatcher("/views/employee-add.jsp");
		
		dispatcher.forward(request, response);
	}
	public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		if(employeeDAO.deleteEmployee(Integer.parseInt(id))) {
			request.setAttribute("message", "deleted succesfully");
		}
		listEmployees(request, response);

	}
}
