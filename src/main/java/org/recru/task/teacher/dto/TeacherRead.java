package org.recru.task.teacher.dto;

import lombok.Getter;
import lombok.Setter;
import org.recru.task.person.dto.PersonRead;
import org.recru.task.subject.UniSubject;

@Getter
@Setter
public class TeacherRead extends PersonRead {
	private UniSubject subject;
}
