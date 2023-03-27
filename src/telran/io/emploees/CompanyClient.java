package telran.io.emploees;

import java.util.Iterator;
import java.util.List;

import telran.net.NetworkClient;

public class CompanyClient<T extends NetworkClient> implements Company{

	private static final long serialVersionUID = 1L;
	
	private T clientCompany;
	
	public CompanyClient(NetworkClient client) {	
		try {
			clientCompany =(T)client;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public boolean addEmployee(Employee employee) {
		return clientCompany.send("add", employee);
	}

	@Override
	public Employee removeEmployee(long id) {
		return clientCompany.send("remove", id);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return clientCompany.send("getAll", null);
	}

	@Override
	public List<Employee> getEmployeesBySalary(int SalaryFrom, int SalaryTo) {
		int[] sal = new int[] {SalaryFrom,SalaryTo};
		return clientCompany.send("getBySalary", sal);
	}

	@Override
	public Employee getEmployee(long id) {
		return clientCompany.send("getEmployee", id);
	}

	@Override
	public void save(String pathName) {
		clientCompany.send("save", pathName);
	}

	@Override
	public void restore(String pathName) {
		clientCompany.send("restore", pathName);

	}

	@Override
	public List<Employee> getEmployeesByMonthBirth(int month) {
		return clientCompany.send("getByMonth", month);
	}

	@Override
	public List<Employee> getEmployeesByDepartment(String department) {
		return clientCompany.send("getByDepartment", department);
	}

	@Override
	public Iterator<Employee> iterator() {
		return clientCompany.send("iter",null);
	}
	
}
