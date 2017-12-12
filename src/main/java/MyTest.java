import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

class Person {
	String name;
	int age;
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
//	@Override
//	public int hashCode() {
//	    return age;
//	}
}
public class MyTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		 Set<Person> people = new HashSet<Person>();
		    Person person = null;
		    for (int i = 0; i < 3 ; i++) {
		        person = new Person("name-" + i, i);
		        people.add(person);
		    }
		    person.age = 100;
		    System.out.println(people.contains(person));
		    people.add(person);
		    System.out.println(people.size());
	}
}