package telran.io.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.io.emploees.Company;
import telran.io.emploees.CompanyImpl;
import telran.io.emploees.Employee;

public class CompanyTest {

	Company company;
	@BeforeEach
	void seyUp() {
		company = new CompanyImpl();
	}
	//@Test
	void addEmployeeAndSaveTest () {
		
		company.addEmployee(new Employee(1, "Vasya", LocalDate.of(1988, 5, 20), "SD", 22000));
		company.addEmployee(new Employee(2, "Petya", LocalDate.of(1992, 4, 30), "OT", 19000));
		company.addEmployee(new Employee(3, "Igor", LocalDate.of(1990, 12, 9), "SD", 20000));
		
		company.save("company.data");
	}
	
	@Test
	void companyRestoreAndUtilsTest () {
	
		company.restore("company.data");
		assertEquals(company.getEmployee(1).getName(),"Vasya");
		assertEquals(company.getAllEmployees().size(), 3);
		assertEquals(company.getEmployeesByDepartment("OT").size(), 1);
		assertEquals(company.getEmployeesByMonthBirth(4).get(0).getName(), "Petya");
		assertEquals(company.getEmployeesBySalary(20000, 25000).size(), 2);
	}
	
	
}
	