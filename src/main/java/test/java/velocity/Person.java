package test.java.velocity;

public class Person {

	public int id;
	public String name;

	public String say() {
		return "我的名字叫: " + name;
	}

	public Person(int i, String string) {
		this.id = i;
		this.name = string;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
