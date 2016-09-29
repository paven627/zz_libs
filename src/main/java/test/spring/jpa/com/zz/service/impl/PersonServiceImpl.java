package test.spring.jpa.com.zz.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import test.spring.jpa.com.zz.model.Person;
import test.spring.jpa.com.zz.service.PersonService;


@Transactional
public class PersonServiceImpl implements PersonService{

	@PersistenceContext
	 EntityManager em;
	
	@Override
	public void delete(Integer id) {
		em.remove(em.getReference(Person.class, id));
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void save(Person person) {
		em.persist(person);
	}

	@Override
	public void update(Person person) {
		em.merge(person);
	}

	@Override
	public List<Person> getPerson(List<String> ids){
		ArrayList<Person> persons = null;
		StringBuffer sb = new StringBuffer("from Person Person where Person.id in (");
		for (int i = 0; i < ids.size(); i++) {
			sb.append("?,");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		System.out.println(sb.toString());
		Query query = em.createQuery(sb.toString());
		for (int i = 0; i < ids.size(); i++) {
			query.setParameter(i+1, Integer.parseInt(ids.get(i)));
		}
		persons = (ArrayList<Person>) query.getResultList();
		return persons;
	}

	@Override
	public int alterTable(){
//		String s = "ALTER TABLE person ADD COLUMN num1 VARCHAR(32);";
		String s = "ALTER TABLE person drop COLUMN num1 ";
//		String s = "insert into person (name) values('aa')";
		return em.createNativeQuery(s)
		 .executeUpdate();
		 
	}
	
	public PersonServiceImpl() {
		super();
	}

}
