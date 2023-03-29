package telran.io.view;

import java.time.LocalDate;

public class ViewApp {

	public static void main (String ... args) {

		Menu menu = new Menu("calculator",
				
				Item.of("Arithmetic opeartions",io ->{
					boolean running = true;
					while (running) {
					double num1 = io.readNumber("Insert first number", "Wrong number", Double.MIN_VALUE, Double.MAX_VALUE);
					String operation = io.readString("Insert operation");
					double num2 = io.readNumber("Insert second number", "Wrong number", Double.MIN_VALUE, Double.MAX_VALUE);
					String sum = switch (operation){
					case "+" -> {running=false; yield String.valueOf(num1+num2);}
					case "-" -> {running=false; yield String.valueOf(num1-num2);}
					default ->"Wrong operation. Only + or -"; 
					};
					io.writeLine(sum);
				}}),
				
				Item.of("Date ooperations", io-> {
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
				}}),
				Item.exit()
				);
			
		menu.perform(new StandardInputOutput());
	}
}
