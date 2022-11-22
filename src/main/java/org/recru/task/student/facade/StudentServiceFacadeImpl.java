package org.recru.task.student.facade;

import lombok.RequiredArgsConstructor;
import org.recru.task.patterns.Facade;
import org.recru.task.person.PersonEntity;
import org.recru.task.student.StudentEntity;
import org.recru.task.student.dto.StudentCreate;
import org.recru.task.student.dto.StudentRead;
import org.recru.task.student.dto.StudentUpdate;
import org.recru.task.student.mapping.StudentCreateMapper;
import org.recru.task.student.mapping.StudentReadMapper;
import org.recru.task.student.mapping.StudentUpdateMapper;
import org.recru.task.student.service.StudentService;
import org.recru.task.teacher.TeacherEntity;
import org.recru.task.teacher.dto.TeacherRead;
import org.recru.task.teacher.mapping.TeacherReadMapper;
import org.recru.task.teacher.service.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Facade
@RequiredArgsConstructor
class StudentServiceFacadeImpl extends StudentServiceFacade {
	private final StudentService studentService;
	private final TeacherService teacherService;
	private final StudentCreateMapper studentCreateMapper;
	private final StudentUpdateMapper studentUpdateMapper;
	private final StudentReadMapper studentReadMapper;
	private final TeacherReadMapper teacherReadMapper;

	@Override
	public PersonEntity.ID createPerson(StudentCreate personEntity) {
		StudentEntity studentEntity = studentCreateMapper.toEntity(personEntity);
		studentEntity = studentService.savePerson(studentEntity);
		return new PersonEntity.ID(studentEntity.getPersonId());
	}

	@Override
	public void updatePerson(StudentUpdate studentUpdate) {
		StudentEntity studentEntity = studentService.getPerson(studentUpdate.getPersonId());
		studentUpdateMapper.updateEntity(studentUpdate, studentEntity);
	}

	@Override
	public void deletePerson(Long studentId) {
		studentService.deletePerson(studentId);
	}

	@Override
	public Set<StudentEntity> getPersonsReferences(List<Long> studentsId) {
		return studentService.getPersonsReferences(studentsId);
	}

	@Override
	public Page<StudentRead> getPersonsPage(Pageable pageable) {
		return studentService.getPersonsPage(pageable)
		                     .map(studentReadMapper::toDTO);
	}

	@Override
	public Set<StudentRead> getPersonsByFirstName(String firstName) {
		return studentService.getPersonsByFirstName(firstName)
		                     .stream()
		                     .map(studentReadMapper::toDTO)
		                     .collect(toSet());
	}

	@Override
	public Set<StudentRead> getPersonsByLastName(String lastName) {
		return studentService.getPersonsByLastName(lastName)
		                     .stream()
		                     .map(studentReadMapper::toDTO)
		                     .collect(toSet());
	}

	@Override
	public Set<StudentRead> getPersonsByFullName(String firstName, String lastName) {
		return studentService.getPersonsByFullName(firstName, lastName)
		                     .stream()
		                     .map(studentReadMapper::toDTO)
		                     .collect(toSet());
	}

	@Override
	public Set<TeacherRead> getTeachersOfStudent(long id) {
		return studentService.getPerson(id)
		                     .getTeachers()
		                     .stream()
		                     .map(teacherReadMapper::toDTO)
		                     .collect(toSet());
	}

	@Override
	@Transactional
	public void addTeacherToStudent(long studentId, long teacherId) {
		StudentEntity studentEntity = studentService.getPersonReference(studentId);
		TeacherEntity teacherEntity = teacherService.getPersonReference(teacherId);
		studentEntity.getTeachers()
		             .add(teacherEntity);
		teacherEntity.getStudents()
		             .add(studentEntity);
	}

	@Override
	@Transactional
	public void removeTeacherFromStudent(long studentId, long teacherId) {
		StudentEntity studentEntity = studentService.getPersonReference(studentId);
		TeacherEntity teacherEntity = teacherService.getPersonReference(teacherId);
		studentEntity.getTeachers()
		             .remove(teacherEntity);
		teacherEntity.getStudents()
		             .remove(studentEntity);
	}
}
