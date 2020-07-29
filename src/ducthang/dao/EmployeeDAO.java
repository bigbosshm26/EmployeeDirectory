package ducthang.dao;

import java.util.List;

import ducthang.entity.Employee;

public interface EmployeeDAO {

	List<Employee> get();
	boolean addEmployee(Employee e);
	Employee readSingleEmployee(int id);
	boolean updateEmployee(Employee e);
	boolean deleteEmployee(int id);
}
