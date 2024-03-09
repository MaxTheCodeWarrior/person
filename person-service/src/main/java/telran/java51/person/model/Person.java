package telran.java51.person.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Person implements Serializable {
	private static final long serialVersionUID = -2812010942738675155L;
	
	@Id
	Integer id;
	
	@Setter
	String name;
	LocalDate birthDate;
	
	@Setter
	// @Embedded not need cos address has annotation @Embeddable
	Address address;
	
	

}
