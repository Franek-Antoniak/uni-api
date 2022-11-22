package org.recru.task.person.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonRead {
	private Long personId;
	private String firstName;
	private String lastName;
	private String email;
}
