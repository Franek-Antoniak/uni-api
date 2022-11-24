package org.recru.task.teacher.facade;

import org.recru.task.person.facade.PersonServiceFacade;
import org.recru.task.student.dto.StudentRead;
import org.recru.task.teacher.dto.TeacherCreate;
import org.recru.task.teacher.dto.TeacherRead;
import org.recru.task.teacher.dto.TeacherUpdate;

import java.util.Set;

public abstract class TeacherServiceFacade extends PersonServiceFacade<TeacherCreate, TeacherUpdate, TeacherRead> {
	public abstract Set<StudentRead> getStudentsOfTeacher(long teacherId);
}
