package org.recru.task.student.dto;

import lombok.Getter;
import lombok.Setter;
import org.recru.task.person.dto.PersonUpdate;
import org.recru.task.subject.validator.UniSubjectConstraint;
import org.recru.task.teacher.validator.TeachersIdConstraint;

import java.util.List;

@Getter
@Setter
public class StudentUpdate extends PersonUpdate {
	@UniSubjectConstraint
	private String major;
	@TeachersIdConstraint
	private List<Long> newTeachers;
}
