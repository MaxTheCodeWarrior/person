package telran.java51.person.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee extends Person {

	private static final long serialVersionUID = 8650681006690753566L;

	String company;
	Integer salary;

	public Employee(Integer id, String name, LocalDate birthDate, Address address, String company, Integer salary) {
		super(id, name, birthDate, address);
		this.company = company;
		this.salary = salary;
	}

}