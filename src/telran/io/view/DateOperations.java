package telran.io.view;

import java.time.LocalDate;

public class DateOperations implements Item {

	private String name;
	
	public DateOperations(String name) {
		this.name = name;
	}

	@Override
	public String displayName() {
		return name;
	}

	@Override
	public void perform(InputOutput io) {
		boolean running = true;
		while(running) {
		LocalDate date = io.readDateISO("Insert date", "Wrong format of date");
		String operation = io.readString("Insert operation: + or -");
		int num = io.readInt("Insert count of days", "Wrong number");
		String res = switch (operation){
		case "+" -> {running = false; yield date.plusDays(num).toString();}
		case "-" -> {running = false; yield date.minusDays(num).toString();}
		default -> "Wrong operation"; 
		};
		io.writeLine(res);
	}
	}

	@Override
	public boolean isExit() {
		return false;
	}

}
