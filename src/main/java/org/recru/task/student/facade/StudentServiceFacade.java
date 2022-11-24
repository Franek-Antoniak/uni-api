package org.recru.task.student.facade;

import org.recru.task.person.facade.PersonServiceFacade;
import org.recru.task.student.dto.StudentCreate;
import org.recru.task.student.dto.StudentRead;
import org.recru.task.student.dto.StudentUpdate;
import org.recru.task.teacher.dto.TeacherRead;

import java.util.Set;

public abstract class StudentServiceFacade extends PersonServiceFacade<StudentCreate, StudentUpdate, StudentRead> {
	public abstract Set<TeacherRead> getTeachersOfStudent(long studentId);

	public abstract void addTeacherToStudent(long studentId, long teacherId);

	public abstract void removeTeacherFromStudent(long studentId, long teacherId);
}
