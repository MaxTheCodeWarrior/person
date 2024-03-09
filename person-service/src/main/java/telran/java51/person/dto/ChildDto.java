package telran.java51.person.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;

@JsonTypeName("child")
@Getter
public class ChildDto extends PersonDto {
	
	String kindergarden;

}
