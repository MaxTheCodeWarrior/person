package telran.java51.person.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java51.person.dto.AddressDto;
import telran.java51.person.dto.PersonDto;
import telran.java51.person.service.PersonService;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
	final PersonService personService;

	@PostMapping
	public Boolean addPesron(@RequestBody PersonDto personDto) {
		return personService.addPerson(personDto);
	}

	@GetMapping("/{id}")
	public PersonDto findPersonById(@PathVariable Integer id) {
		return personService.findPersonById(id);
	}

	@GetMapping("/city/{city}")
	public List<PersonDto> findPersonsByCity(@PathVariable String city) {
		return personService.findPersonsByCity(city);
	}

	@GetMapping("/ages/{from}/{to}")
	public List<PersonDto> findPersonsByAges(@PathVariable Integer from, @PathVariable Integer to) {
		return personService.findPersonsByAges(from, to);
	}

	@PutMapping("/{id}/name/{newName}")
	public PersonDto updatePersonName(@PathVariable Integer id, @PathVariable String newName) {
		return personService.updatePersonName(id, newName);
	}

	@GetMapping("/name/{name}")
	public List<PersonDto> findPersonByName(@PathVariable String name) {
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

}