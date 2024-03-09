package telran.java51.person.service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.java51.person.dao.PersonRepository;
import telran.java51.person.dto.AddressDto;
import telran.java51.person.dto.ChildDto;
import telran.java51.person.dto.CityPopulationDto;
import telran.java51.person.dto.EmployeeDto;
import telran.java51.person.dto.PersonDto;
import telran.java51.person.dto.exceptions.PersonNotFoundException;
import telran.java51.person.model.Address;
import telran.java51.person.model.Child;
import telran.java51.person.model.Employee;
import telran.java51.person.model.Person;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService, CommandLineRunner {

	final PersonRepository personRepository;
	final ModelMapper modelMapper;

	@Transactional
	@Override
	public Boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			return false;
		}
		if (personDto instanceof ChildDto childDto) {
			personRepository.save(modelMapper.map(childDto, Child.class));
			return true;
		}
		if (personDto instanceof EmployeeDto employeeDto) {
			personRepository.save(modelMapper.map(employeeDto, Employee.class));
			return true;
		}
		personRepository.save(modelMapper.map(personDto, Person.class));
		return true;
	}

	@Transactional(readOnly = true)
	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		if (person instanceof Child child) {
			return modelMapper.map(child, ChildDto.class);
		}
		if (person instanceof Employee employee) {
			return modelMapper.map(employee, EmployeeDto.class);
		}
		return modelMapper.map(person, PersonDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<PersonDto> findPersonsByCity(String city) {

		return personRepository.findByAddressCity(city).map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());

	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<PersonDto> findPersonsByAges(Integer from, Integer to) {
		return personRepository.findByAgeBetween(from, to).map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());

	}

	@Transactional
	@Override
	public PersonDto updatePersonName(Integer id, String newName) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		person.setName(newName);
		return modelMapper.map(person, PersonDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<PersonDto> findPersonsByName(String name) {
		return personRepository.findPersonsByName(name).map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public PersonDto updatePersonAddress(Integer id, AddressDto newAddress) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		Address address = new Address(newAddress.getCity(), newAddress.getStreet(), newAddress.getBuilding());
		person.setAddress(address);
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
	public Iterable<CityPopulationDto> getCitiesPopulation() {
		return personRepository.getCityPopulation().collect(Collectors.toList());
	}

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		if (personRepository.count() == 0) {
			Person person = new Person(1000, "John", LocalDate.of(1985, 3, 11),
					new Address("Tel Aviv", "Ben Gvirol", 81));
			Child child = new Child(2000, "Mosche", LocalDate.of(2018, 7, 5),
					new Address("Ashkelon", "Bar Kohva", 21), "Shalom");
			Employee employee = new Employee(3000, "Sarah", LocalDate.of(1995, 11, 23),
					new Address("Rehovot", "Herzl", 7), "Motorola", 20_000);
			personRepository.save(person);
			personRepository.save(child);
			personRepository.save(employee);

		}

	}
	@Transactional(readOnly = true)
	@Override
	public Iterable<ChildDto> findAllChildren() {
		
	return	personRepository.findAllChildren().map(p -> modelMapper.map(p, ChildDto.class))
		.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<EmployeeDto> findEmployeesBySalary(Integer from, Integer to) {
		return personRepository.findEmployeesBySalaryBetween(from, to).map(p -> modelMapper.map(p, EmployeeDto.class))
		.collect(Collectors.toList());
	}

}
