package telran.java51.person.service;

import telran.java51.person.dto.AddressDto;
import telran.java51.person.dto.ChildDto;
import telran.java51.person.dto.CityPopulationDto;
import telran.java51.person.dto.EmployeeDto;
import telran.java51.person.dto.PersonDto;

public interface PersonService {

	Boolean addPerson(PersonDto personDto);

	PersonDto findPersonById(Integer id);

	Iterable<PersonDto> findPersonsByCity(String city);

	Iterable<PersonDto> findPersonsByAges(Integer from, Integer to);
	
	Iterable<CityPopulationDto> getCitiesPopulation();

	PersonDto updatePersonName(Integer id, String newName);

	Iterable<PersonDto> findPersonsByName(String name);

	PersonDto updatePersonAddress(Integer id, AddressDto newAddress);

	PersonDto deletePersonById(Integer id);

	Iterable<ChildDto> findAllChildren();

	Iterable<EmployeeDto> findEmployeesBySalary(Integer from, Integer to);

}
