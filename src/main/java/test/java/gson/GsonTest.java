package test.java.gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONArray;

public class GsonTest {
	static Gson gson = new Gson();
	// final static Gson GSON = new GsonBuilder().
	// registerTypeAdapter(SohuAdxBizResponse.class, new
	// SohuAdxBizResponseDeserializer())
	// .create();
	public static void main(String[] args) {
		// gsonTest();
		// jsonObjectTest();
//		gsonBuilderTest();
		
		Integer fromJson = gson.fromJson("100", int.class);
		System.out.println(fromJson);
		
	}

	private static void gsonBuilderTest() {
		Gson gson = new GsonBuilder().registerTypeAdapter(Person.class, new PersonAdapter()).create();
		List<Person> persons = getPersons();
		String str = gson.toJson(persons);
		
//		TypeToken<List<String>> list = new TypeToken<List<String>>() {};
		
		Type type = new TypeToken<List<Person>>(){}.getType();
		Object fromJson = gson.fromJson(str, type);

		System.out.println(fromJson);

	}

	private static void gsonTest() {
		Gson gson = new Gson();
		List<Person> persons = getPersons();

		String str = gson.toJson(persons);
		System.out.println(str);

		ArrayList fromJson = gson.fromJson(str, ArrayList.class);

		System.out.println(fromJson);
	}

	private static List<Person> getPersons() {
		List<Person> persons = new ArrayList<Person>();
		Person p = new Person();
		p.setName("name");

		Person p1 = new Person();
		p1.setAge(1);

		Person p2 = new Person();

		persons.add(p);
		persons.add(p1);
		persons.add(p2);
		return persons;
	}

	public static void jsonObjectTest() {
		List<Person> persons = getPersons();

		JSONArray str = JSONArray.fromObject(persons);
		System.out.println(str);
	}
}
