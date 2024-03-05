package telran.java51.person.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDto {
	Integer id;
	String name;
	LocalDate birthDate;
	AddressDto address;

}
