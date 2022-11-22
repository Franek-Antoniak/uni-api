package org.recru.task.teacher.dto;

import lombok.Getter;
import lombok.Setter;
import org.recru.task.person.dto.PersonUpdate;
import org.recru.task.student.validator.StudentsIdConstraint;
import org.recru.task.subject.validator.UniSubjectConstraint;

import java.util.List;

@Getter
@Setter
public class TeacherUpdate extends PersonUpdate {
	@UniSubjectConstraint
	private String subject;
	@StudentsIdConstraint
	private List<Long> studentsId;
}
