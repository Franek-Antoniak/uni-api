package org.recru.task.teacher.facade;

import lombok.RequiredArgsConstructor;
import org.recru.task.patterns.Facade;
import org.recru.task.person.PersonEntity;
import org.recru.task.student.dto.StudentRead;
import org.recru.task.student.mapping.StudentReadMapper;
import org.recru.task.teacher.TeacherEntity;
import org.recru.task.teacher.dto.TeacherCreate;
import org.recru.task.teacher.dto.TeacherRead;
import org.recru.task.teacher.dto.TeacherUpdate;
import org.recru.task.teacher.mapping.TeacherCreateMapper;
import org.recru.task.teacher.mapping.TeacherReadMapper;
import org.recru.task.teacher.mapping.TeacherUpdateMapper;
import org.recru.task.teacher.service.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Facade
@RequiredArgsConstructor
@Validated
class TeacherServiceFacadeImpl extends TeacherServiceFacade {
	private final TeacherService teacherService;
	private final TeacherReadMapper teacherReadMapper;
	private final TeacherCreateMapper teacherCreateMapper;
	private final TeacherUpdateMapper teacherUpdateMapper;
	private final StudentReadMapper studentReadMapper;

	@Override
	public PersonEntity.ID createPerson(@Valid TeacherCreate personEntity) {
		TeacherEntity entity = teacherService.savePerson(teacherCreateMapper.toEntity(personEntity));
		return new PersonEntity.ID(entity.getPersonId());
	}

	@Transactional
	@Override
	public void updatePerson(@Valid TeacherUpdate personUpdate) {
		TeacherEntity entity = teacherService.getPerson(personUpdate.getPersonId());
		teacherUpdateMapper.updateEntity(personUpdate, entity);
	}

	@Override
	public void deletePerson(Long id) {
		teacherService.deletePerson(id);
	}

	@Override
	public Page<TeacherRead> getPersonsPage(Pageable pageable) {
		Page<TeacherEntity> page = teacherService.getPersonsPage(pageable);
		return page.map(teacherReadMapper::toDTO);
	}

	@Override
	public Set<TeacherRead> getPersonsByName(String firstName, String lastName) {
		if (firstName == null && lastName == null) {
			return Set.of();
		}
		if (firstName == null) {
			return teacherService.getPersonsByLastName(lastName)
			                     .stream()
			                     .map(teacherReadMapper::toDTO)
			                     .collect(toSet());
		}
		if (lastName == null) {
			return teacherService.getPersonsByFirstName(firstName)
			                     .stream()
			                     .map(teacherReadMapper::toDTO)
			                     .collect(toSet());
		}
		return teacherService.getPersonsByFullName(firstName, lastName)
		                     .stream()
		                     .map(teacherReadMapper::toDTO)
		                     .collect(toSet());
	}

	@Override
	public Set<StudentRead> getStudentsOfTeacher(long id) {
		return teacherService.getPerson(id)
		                     .getStudents()
		                     .stream()
		                     .map(studentReadMapper::toDTO)
		                     .collect(toSet());
	}
}
