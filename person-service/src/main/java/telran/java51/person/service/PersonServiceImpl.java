package telran.java51.person.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import telran.java51.person.dao.PersonRepository;
import telran.java51.person.dto.AddressDto;
import telran.java51.person.dto.CityPopulationDto;
import telran.java51.person.dto.PersonDto;
import telran.java51.person.dto.exceptions.PersonNotFoundException;
import telran.java51.person.model.Person;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

	final PersonRepository personRepository;
	final ModelMapper modelMapper;

	@Transactional
	@Override
	public Boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			return false;
		}
		personRepository.save(modelMapper.map(personDto, Person.class));
		return true;
	}

	@Transactional(readOnly = true)
	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		return modelMapper.map(person, PersonDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public List<PersonDto> findPersonsByCity(String city) {

		return personRepository.findByAddressCity(city).stream().map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());

	}

	@Transactional(readOnly = true)
	@Override
	public List<PersonDto> findPersonsByAges(Integer from, Integer to) {
		return personRepository.findByAgeBetween(from, to).stream().map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());

	}

	@Transactional
	@Override
	public PersonDto updatePersonName(Integer id, String newName) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		person.setName(newName);
		// personRepository.save(person); //transaction means commit (fix) in the end of
		// the methods
		return modelMapper.map(person, PersonDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public List<PersonDto> findPersonsByName(String name) {
		return personRepository.findPersonsByName(name).stream().map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public PersonDto updatePersonAddress(Integer id, AddressDto newAddress) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		person.getAddress().setCity(newAddress.getCity());
		person.getAddress().setStreet(newAddress.getStreet());
		person.getAddress().setBuilding(newAddress.getBuilding());

		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Transactional
	@Override
	public PersonDto deletePersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		personRepository.deleteById(id);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public List<CityPopulationDto> getCitiesPopulation() {
		return personRepository.getCityPopulation();
	}

}
