package org.recru.task.person;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class PersonEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long personId;
	protected String firstName;
	protected String lastName;
	protected short age;
	protected String email;

	public record ID(Long id) {
	}
}
