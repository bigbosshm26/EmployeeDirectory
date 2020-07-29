package ducthang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ducthang.entity.Employee;
import ducthang.util.DBConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	Connection connection = null;
	Statement statement = null;
	PreparedStatement prepareStatement = null;
	ResultSet resultSet = null;
	@Override
	public List<Employee> get() {
		//Create reference variables
		List<Employee> list = null;
		Employee employee = null;
				
		try {
			list = new ArrayList<Employee>();
			// Create a sql query 
			String sql = "SELECT * FROM tbl_employee";
			//get the connection
			connection = DBConnectionUtil.openConnection();
			// Create a statement
			statement = connection.createStatement();
			// Execute the query
			resultSet = statement.executeQuery(sql);
			// Process the resultset
			while(resultSet.next()) {
			// Add employees to list
			employee = new Employee();
			employee.setId(resultSet.getInt("id"));
			employee.setName(resultSet.getString("name"));
			employee.setDob(resultSet.getString("dob"));
			employee.setDepartment(resultSet.getString("department"));
			list.add(employee);
			}
			}catch(SQLException e) {
			e.printStackTrace();
			}
			//return the list
			return list;
	}
	
	

	@Override
	public boolean updateEmployee(Employee e) {
		boolean rs;
		try {
			connection = DBConnectionUtil.openConnection();
			prepareStatement = connection.prepareStatement("UPDATE tbl_employee SET name=?, dob=?, department=? WHERE ID=?");
			prepareStatement.setString(1, e.getName());
			prepareStatement.setString(2, e.getDob());
			prepareStatement.setString(3, e.getDepartment());
			prepareStatement.setInt(4, e.getId());

			prepareStatement.executeUpdate();
			rs = true;
		}catch(SQLException ex) {
			ex.printStackTrace();
			rs = false;
		}
			
		return rs;
	}
	@Override
	public Employee readSingleEmployee(int id) {
		Employee employee = null;
		try {
			employee = new Employee();
			connection = DBConnectionUtil.openConnection();
			prepareStatement = connection.prepareStatement("SELECT * FROM tbl_employee WHERE id = ?");
			prepareStatement.setInt(1, id);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {			
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setDob(resultSet.getString("dob"));
				employee.setDepartment(resultSet.getString("department"));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return employee;
	}

	@Override
	public boolean deleteEmployee(int id) {
		boolean rs;
		try {
			connection = DBConnectionUtil.openConnection();
			prepareStatement = connection.prepareStatement("DELETE FROM tbl_employee WHERE id = ?");
			prepareStatement.setInt(1, id);
			prepareStatement.execute();
			rs = true;
		}catch(SQLException ex) {
			ex.printStackTrace();
			rs = false;
		}
		return rs;
	}

	@Override
	public boolean addEmployee(Employee e) {
		boolean rs;
		try {
			connection = DBConnectionUtil.openConnection();
			prepareStatement = connection.prepareStatement("INSERT INTO tbl_employee(name,dob,department) VALUES(?,?,?)");
			prepareStatement.setString(1, e.getName());
			prepareStatement.setString(2, e.getDob());
			prepareStatement.setString(3, e.getDepartment());
			prepareStatement.executeUpdate();
			rs = true;
		}catch(SQLException ex) {
			ex.printStackTrace();
			rs = false;
		}
			
		return rs;
	}
	


	
	
}
