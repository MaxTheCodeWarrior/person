package telran.java51.person.dao;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import telran.java51.person.dto.CityPopulationDto;
import telran.java51.person.model.Child;
import telran.java51.person.model.Employee;
import telran.java51.person.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

	Stream<Person> findByAddressCity(String city);

	@Query("SELECT p FROM Person p WHERE YEAR(CURRENT_DATE) - YEAR(p.birthDate) BETWEEN :from AND :to")
	Stream<Person> findByAgeBetween(@Param("from") Integer from, @Param("to") Integer to);

	@Query("SELECT new telran.java51.person.dto.CityPopulationDto(p.address.city, COUNT(p)) FROM Person p GROUP BY p.address.city ORDER BY COUNT(p) DESC")
	Stream<CityPopulationDto> getCityPopulation();

	Stream<Person> findPersonsByName(String name);
	
	//@Query("SELECT c FROM Child c WHERE TYPE(c) = Child")
	Stream<Child> findChildrenBy();

	Stream<Employee> findEmployeesBySalaryBetween(Integer from, Integer to);

}
