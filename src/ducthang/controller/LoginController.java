package ducthang.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ducthang.dao.LoginDAO;
import ducthang.dao.LoginDAOImpl;
import ducthang.entity.Login;


public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    LoginDAO loginDAO = null;
    @Override
    public void init() throws ServletException {
    	super.init();
    	loginDAO = new LoginDAOImpl();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Login login = new Login();
		login.setEmail(email);
		login.setPassword(password);
		String status = loginDAO.authenticate(login);
		if(status.equals("true")) {
			session.setAttribute("email", email);
			response.sendRedirect("EmployeeController?action=LIST");
		}
		if(status.equals("false")) {
			response.sendRedirect("index.jsp?status=false");
		}
		if(status.equals("error")) {
			response.sendRedirect("index.jsp?status=error");
		}
	}

}
