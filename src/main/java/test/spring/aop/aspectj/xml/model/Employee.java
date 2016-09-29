package test.spring.aop.aspectj.xml.model;

public class Employee {
	private String empName;

	public void qqGame() {
		System.out.println(empName + " 正在聊QQ");
	}

	public void mmGame() {
		System.out.println("泡MM");
	}

	public void work() {
		System.out.println("在工作");
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

}
