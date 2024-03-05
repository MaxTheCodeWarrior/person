package telran.java51.person.service;

import java.util.List;

import telran.java51.person.dto.AddressDto;
import telran.java51.person.dto.PersonDto;

public interface PersonService {

	Boolean addPerson(PersonDto personDto);

	PersonDto findPersonById(Integer id);

	List<PersonDto> findPersonsByCity(String city);

	List<PersonDto> findPersonsByAges(Integer from, Integer to);

	PersonDto updatePersonName(Integer id, String newName);

	List<PersonDto> findPersonsByName(String name);

	PersonDto updatePersonAddress(Integer id, AddressDto newAddress);

	PersonDto deletePersonById(Integer id);

}
