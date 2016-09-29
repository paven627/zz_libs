package test.java.reflection.mldn;

interface China { // ����China�ӿ�
	public static final String NATIONAL = "China"; // ����ȫ�ֳ���
	public static final String AUTHOR = "���˻�"; // ����ȫ�ֳ���

	public void sayChina(); // �޲εģ�û�з���ֵ�ķ���

	public String sayHello(String name, int age); // ��������������ķ���������������
}

public class Person implements China {
	private String name;
	private int age;

	public Person() { // �޲ι���
	}

	public Person(String name) {
		this.name = name; // ����name����
	}

	public Person(String name, int age) {
		this(name);
		this.age = age;
	}

	public void sayChina() { // ��д����
		System.out.println("���ߣ�" + AUTHOR + "����" + NATIONAL);
	}

	public String sayHello(String name, int age) {
		return name + "����ã��ҽ��꣺" + age + "���ˣ�";
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return this.name;
	}

	public int getAge() {
		return this.age;
	}
};