package telran.io.objectstreams;

public class RestorePersonsAppl {


	public static void main(String[] args) throws Exception {
		Persons persons = Persons.restore();
		
		persons.forEach(System.out::println);

	}
}
