package org.recru.task.teacher.dto;

import lombok.Getter;
import lombok.Setter;
import org.recru.task.person.dto.PersonCreate;
import org.recru.task.student.validator.StudentsIdConstraint;
import org.recru.task.subject.validator.UniSubjectConstraint;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class TeacherCreate extends PersonCreate {
	@NotNull
	@UniSubjectConstraint
	private String subject;
	@StudentsIdConstraint
	private List<Long> students;
}
