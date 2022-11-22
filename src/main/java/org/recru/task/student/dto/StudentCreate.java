package org.recru.task.student.dto;

import lombok.Getter;
import lombok.Setter;
import org.recru.task.major.validator.UniMajorConstraint;
import org.recru.task.person.dto.PersonCreate;
import org.recru.task.teacher.validator.TeachersIdConstraint;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class StudentCreate extends PersonCreate {
	@NotNull
	@UniMajorConstraint
	private String major;
	@TeachersIdConstraint
	private List<Long> teachers;
}
