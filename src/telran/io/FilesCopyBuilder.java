package telran.io;

public class FilesCopyBuilder {

	public static void main(String[] args) {
		
		Copy copy = null;
		if (args.length!=0 && args[0]!= null && args[1]!= null && args[2]!= null && args[3] != null ) {
			String copyStrategy = args[0].toLowerCase();
			switch (copyStrategy) {
				case "file":
					copy = new FilesCopy(args[1], args[2], Boolean.parseBoolean(args[3]));
					break;
				case "transfer":
					copy = new TransferCopy(args[1], args[2], Boolean.parseBoolean(args[3]));
					break;
				case "buffer":
					copy = new BufferCopy(args[1], args[2], Boolean.parseBoolean(args[3]));
					break;
			}
			copy.copyRun();
		} else {
			System.out.println("Required parameters are not entered!");
		}
	}

}
