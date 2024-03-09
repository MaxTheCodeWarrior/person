package telran.java51.person.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.RequiredArgsConstructor;
import telran.java51.person.dto.AddressDto;
import telran.java51.person.dto.ChildDto;
import telran.java51.person.dto.CityPopulationDto;
import telran.java51.person.dto.EmployeeDto;
import telran.java51.person.dto.PersonDto;
import telran.java51.person.service.PersonService;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

	final PersonService personService;
	final ObjectMapper objectMapper;

	@PostMapping
	public Boolean addPerson(@RequestBody String requestBody) {
		try {
			if (requestBody.contains("type")) {
				return personService.addPerson(objectMapper.readValue(requestBody, PersonDto.class));
			} else {
				ObjectNode jsonNode = objectMapper.readValue(requestBody, ObjectNode.class);
				jsonNode.put("type", "person");
				String modifiedRequestBody = objectMapper.writeValueAsString(jsonNode);
				PersonDto personDto = objectMapper.readValue(modifiedRequestBody, PersonDto.class);
				return personService.addPerson(personDto);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@GetMapping("/{id}")
	public PersonDto findPersonById(@PathVariable Integer id) {
		return personService.findPersonById(id);
	}

	@GetMapping("/city/{city}")
	public Iterable<PersonDto> findPersonsByCity(@PathVariable String city) {
		return personService.findPersonsByCity(city);
	}

	@GetMapping("/ages/{from}/{to}")
	public Iterable<PersonDto> findPersonsByAges(@PathVariable Integer from, @PathVariable Integer to) {
		return personService.findPersonsByAges(from, to);
	}

	@PutMapping("/{id}/name/{newName}")
	public PersonDto updatePersonName(@PathVariable Integer id, @PathVariable String newName) {
		return personService.updatePersonName(id, newName);
	}

	@GetMapping("/population/city")
	public Iterable<CityPopulationDto> getCitiesPopulation() {
		return personService.getCitiesPopulation();
	}

	@GetMapping("/name/{name}")
	public Iterable<PersonDto> findPersonsByName(@PathVariable String name) {
		return personService.findPersonsByName(name);
	}

	@PutMapping("/{id}/address")
	public PersonDto updatePersonAddress(@PathVariable Integer id, @RequestBody AddressDto newAddress) {
		return personService.updatePersonAddress(id, newAddress);
	}

	@DeleteMapping("/{id}")
	public PersonDto deletePersonById(@PathVariable Integer id) {
		return personService.deletePersonById(id);
	}

	@GetMapping("/children")
	public Iterable<ChildDto> findAllChildren() {
		return personService.findAllChildren();
	}

	@GetMapping("/salary/{from}/{to}")
	public Iterable<EmployeeDto> findEmployeesBySalary(@PathVariable Integer from, @PathVariable Integer to) {
		return personService.findEmployeesBySalary(from, to);
	}

}