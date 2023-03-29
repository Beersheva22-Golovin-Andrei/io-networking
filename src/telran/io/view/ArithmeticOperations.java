package telran.io.view;

public class ArithmeticOperations implements Item {

	private String name;
	
	public ArithmeticOperations(String name) {
		this.name = name;
	}

	@Override
	public String displayName() {
		return name;
	}

	@Override
	public void perform(InputOutput io) {
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
	}
	}

	@Override
	public boolean isExit() {
		return false;
	}

}
