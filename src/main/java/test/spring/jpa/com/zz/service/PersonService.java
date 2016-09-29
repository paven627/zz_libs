package test.spring.jpa.com.zz.service;

import java.util.List;

import test.spring.jpa.com.zz.model.Person;

public interface PersonService {
	void save(Person person);
	
	void update(Person person);
	
	void delete(Integer id);

	List<Person> getPerson(List<String> ids);

	int alterTable();
}
