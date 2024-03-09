package telran.java51.person.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.Setter;


@JsonTypeName("child")
@Getter
@Setter
public class ChildDto extends PersonDto {

	String kindergarten;

}
