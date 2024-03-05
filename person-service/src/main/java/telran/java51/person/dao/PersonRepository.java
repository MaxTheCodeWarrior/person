package telran.java51.person.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import telran.java51.person.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

	List<Person> findByAddressCity(String city);
	
	
	@Query("SELECT p FROM Person p WHERE YEAR(CURRENT_DATE) - YEAR(p.birthDate) BETWEEN :from AND :to")
    List<Person> findByAgeBetween(@Param("from") Integer from, @Param("to") Integer to);
    
	
	List<Person> findPersonsByName(String name);
}
