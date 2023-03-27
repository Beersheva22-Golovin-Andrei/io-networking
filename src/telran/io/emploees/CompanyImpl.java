package telran.io.emploees;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CompanyImpl implements Company {
	
	private static final Logger LOGGER = Logger.getLogger("Company");
	
	private static final long serialVersionUID = 1L;
	
	private Map <Long, Employee> emplId = new HashMap<>();
	
	private Map <Integer, List<Employee>> emplMonth = new HashMap<>();
	
	private Map <Integer, List<Employee>> emplSel = new HashMap<>();
	
	private Map <String, List<Employee>> emplDepart = new HashMap<>();


	@Override
	public Iterator<Employee> iterator() {
		return emplId.values().iterator();
	}

	@Override
	public boolean addEmployee(Employee empl) {
		Employee res = emplId.put(empl.getId(), empl);
		if (res == null) {
			Integer salary = empl.getSalary();
			addToOthersMaps(emplSel, salary, empl);
			String depart = empl.getDepartment();
			addToOthersMaps(emplDepart, depart, empl);
			Integer month = empl.getBirthDate().getMonthValue();
			addToOthersMaps(emplMonth, month, empl);
		}
		return res == null;
	}
	
	private <T> void addToOthersMaps (Map<T, List<Employee>> map, T key, Employee value) {
		if(map.containsKey(key)) {
			map.get(key).add(value);
		} else {
			List<Employee> list =new ArrayList<>();
			list.add(value);
			map.put(key, list);
		}
	}

	@Override
	public Employee removeEmployee(long id) {
		Employee res = emplId.remove(id);
		if (res!=null) {
			Integer salary = res.getSalary();
			removeFromOthersMaps(emplSel, salary, res);
			String depart = res.getDepartment();
			removeFromOthersMaps(emplDepart, depart, res);
			Integer month = res.getBirthDate().getMonthValue();
			removeFromOthersMaps(emplMonth, month, res);
		}
		return res;
	}
	
	private <T> void removeFromOthersMaps (Map<T, List<Employee>> map, T key, Employee value) {
		List<Employee> list = map.get(key);
		if (list.size()==1) {
			map.remove(key);
		} else {
			list.remove(value);
		}
	}

	@Override
	public List<Employee> getAllEmployees() {
		return new ArrayList<>(emplId.values());
	}

	@Override
	public List<Employee> getEmployeesByMonthBirth(int month) {
		return new ArrayList<>(emplMonth.get(month));
	}

	@Override
	public List<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
		Set<Integer> keysForUnion = emplSel.keySet().stream().filter(sal -> sal>=salaryFrom && sal<=salaryTo).collect(Collectors.toSet());
		List<Employee> res = new ArrayList<>();
		keysForUnion.forEach(key->res.addAll(emplSel.get(key)));
		return res;
	}

	@Override
	public List<Employee> getEmployeesByDepartment(String department) {
		return new ArrayList<>(emplDepart.get(department));
	}

	@Override
	public Employee getEmployee(long id) {
		return emplId.get(id);
	}

	@Override
	public void save(String pathName) {
		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(pathName))){
			output.writeObject(getAllEmployees());
		} catch(Exception e) {
			throw new RuntimeException(e.toString());
	}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void restore(String pathName) {
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(pathName))) {
			List<Employee> allEmployees = (List<Employee>) input.readObject();
			allEmployees.forEach(this::addEmployee);
		}catch(FileNotFoundException e) {
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		}

	}
}
