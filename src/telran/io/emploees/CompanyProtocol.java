package telran.io.emploees;

import java.io.Serializable;

import telran.net.Protocol;
import telran.net.Request;
import telran.net.Response;
import telran.net.ResponseCode;

public class CompanyProtocol implements Protocol{

	private Company company = new CompanyImpl();
	@Override
	public Response getResponse(Request request) {
		Response resp = switch(request.type) {
			case "add" -> addEmployee(request.data);
			case "remove" -> removeEmployee(request.data);
			case "getAll" -> getAllEmployees(request.data);
			case "getByMonth" -> getEmployeesByMonth(request.data);
			case "getBySalary" -> getEmployeesBySalary(request.data);
			case "getByDepartment" -> getEmployeesByDepartment(request.data);
			case "iter" -> getIterator();
			case "get" -> getEmployee(request.data);
			case "save" -> save(request.data);
			case "restore" -> restore(request.data);
			default -> new Response(ResponseCode.WRONG_REQUEST, request.data);
		};
		return resp;
	}
	private Response getIterator() {
		return new Response (ResponseCode.OK, (Serializable)company.iterator());
	}
	private Response restore(Serializable data) {
		company.restore((String)data);
		return new Response (ResponseCode.OK, null);
	}
	private Response save(Serializable data) {
		company.save((String)data);
		return new Response (ResponseCode.OK,null);
	}
	private Response getEmployee(Serializable data) {
		return new Response (ResponseCode.OK, (Serializable)company.getEmployee((long)data));
	}
	private Response getEmployeesByDepartment(Serializable data) {
		return new Response (ResponseCode.OK, (Serializable)company.getEmployeesByDepartment((String)data));
	}
	private Response getEmployeesBySalary(Serializable data) {
		int[] salRange = (int[])data;
		return new Response (ResponseCode.OK, (Serializable)company.getEmployeesBySalary(salRange[0], salRange[1]));
	}
	private Response getEmployeesByMonth(Serializable data) {
		return new Response (ResponseCode.OK, (Serializable)company.getEmployeesByMonthBirth((int)data));
	}
	private Response getAllEmployees(Serializable data) {
		return new Response (ResponseCode.OK, (Serializable)company.getAllEmployees());
	}
	private Response removeEmployee(Serializable data) {
		return  new Response (ResponseCode.OK, (Serializable)company.removeEmployee((long)data));
	}
	private Response addEmployee(Serializable data) {
		return  new Response (ResponseCode.OK, (Serializable)company.addEmployee((Employee)data));
	}
	
	
	
	

}
