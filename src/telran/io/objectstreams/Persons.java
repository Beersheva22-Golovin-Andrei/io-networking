package telran.io.objectstreams;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Persons implements Serializable, Iterable<Person>{

	static String filePath = "persons.data";
	private static final long serialVersionUID = 1L;
	List<Person> persons = new ArrayList<>();
	
	void addPerson(Person person) {
		persons.add(person);
	}
	@Override
	public Iterator<Person> iterator() {
		
		return persons.iterator();
	}
	public void save() {
		try {
			writeObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static public Persons restore() {
		Persons res = null;
		try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(filePath))) {
			res = (Persons) input.readObject();
		} catch (Exception e) {
			res = new Persons();
		}
		return res;
	}
	
	private void writeObject() throws Exception {
		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filePath))) {
			output.writeObject(this);
		} 
		
	}
}
