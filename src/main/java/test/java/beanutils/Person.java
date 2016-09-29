package test.java.beanutils;

import java.util.Date;

public class Person {
	private Date birthday = new Date();

	private Dept dept;

	private String name;

	private int age;

	public int getAge() {
		return age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public String getName() {
		return name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person() {
		super();
	}

	@Override
	public String toString() {
		return "Person [birthday=" + birthday + ", dept=" + dept + ", name="
				+ name + ", age=" + age + "]";
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

}
