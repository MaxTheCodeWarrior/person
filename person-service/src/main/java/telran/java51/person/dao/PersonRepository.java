package telran.java51.person.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import telran.java51.person.dto.CityPopulationDto;
import telran.java51.person.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

	List<Person> findByAddressCity(String city);
	
	//JPQL
	@Query("SELECT p FROM Person p WHERE YEAR(CURRENT_DATE) - YEAR(p.birthDate) BETWEEN :from AND :to")
    List<Person> findByAgeBetween(@Param("from") Integer from, @Param("to") Integer to);
    
	//TODO RTFM!
	//JPQL - object oriented sql query language 
    @Query("SELECT new telran.java51.person.dto.CityPopulationDto(p.address.city, COUNT(p)) FROM Person p GROUP BY p.address.city ORDER BY COUNT(p) DESC")
	    List<CityPopulationDto> getCityPopulation();
	
	
	//sql native query is anti-patter 
	
	// or java api criteria sql
/*
 * 
 * To avoid DB specialization 
 * 
 * default List<Person> findByAgeBetween(Integer from, Integer to) {
        LocalDate currentDate = LocalDate.now();
        return findAllByBirthDateBetween(currentDate.minusYears(to), currentDate.minusYears(from));
    }

    List<Person> findAllByBirthDateBetween(LocalDate from, LocalDate to);
 * 
 * 
 * 
 */
	
	
	List<Person> findPersonsByName(String name);
}
