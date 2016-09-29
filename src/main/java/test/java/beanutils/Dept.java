package test.java.beanutils;

import java.util.HashMap;
import java.util.Map;

/**
 * 部门
 */
public class Dept {
	private String deptName;
	private int personNum;

	private Map<String, Person> persons = new HashMap<String, Person>();

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getPersonNum() {
		return personNum;
	}

	public void setPersonNum(int personNum) {
		this.personNum = personNum;
	}

	public Map<String, Person> getPersons() {
		return persons;
	}

	public void setPersons(Map<String, Person> persons) {
		this.persons = persons;
	}

	@Override
	public String toString() {
		return "Dept [deptName=" + deptName + ", personNum=" + personNum + "]";
	}


}
