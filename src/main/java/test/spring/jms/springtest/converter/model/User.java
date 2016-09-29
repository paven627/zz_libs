package test.spring.jms.springtest.converter.model;

public class User {
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}

	private String name;
	private int id;

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

}
