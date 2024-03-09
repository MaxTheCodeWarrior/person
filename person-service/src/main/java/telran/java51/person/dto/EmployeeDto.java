package telran.java51.person.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.Setter;

@JsonTypeName("employee")
@Getter
@Setter
public class EmployeeDto extends PersonDto {

	String company;
	Integer salary;

}
