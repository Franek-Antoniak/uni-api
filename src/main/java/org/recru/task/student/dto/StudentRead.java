package org.recru.task.student.dto;

import lombok.Getter;
import lombok.Setter;
import org.recru.task.major.UniMajor;
import org.recru.task.person.dto.PersonRead;

@Setter
@Getter
public class StudentRead extends PersonRead {
	private UniMajor major;
}
