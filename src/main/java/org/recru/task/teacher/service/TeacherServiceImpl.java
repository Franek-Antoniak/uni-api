package org.recru.task.teacher.service;

import lombok.RequiredArgsConstructor;
import org.recru.task.teacher.TeacherEntity;
import org.recru.task.teacher.exception.TeacherNotFoundException;
import org.recru.task.teacher.repository.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
class TeacherServiceImpl extends TeacherService {
	private final TeacherRepository teacherRepository;

	@Override
	public TeacherEntity savePerson(TeacherEntity personEntity) {
		return teacherRepository.save(personEntity);
	}

	@Override
	public void deletePerson(Long id) {
		teacherRepository.deleteById(id);
	}

	@Override
	public TeacherEntity getPerson(Long id) {
		return teacherRepository.findById(id)
		                        .orElseThrow(() -> new TeacherNotFoundException(id));
	}

	@Override
	public Page<TeacherEntity> getPersonsPage(Pageable pageable) {
		return teacherRepository.findAll(pageable);
	}

	@Override
	public Set<TeacherEntity> getPersonsReferences(List<Long> persons) {
		return teacherRepository.getAllByPersonIdIn(persons);
	}

	@Override
	public Set<TeacherEntity> getPersonsByFirstName(String firstName) {
		return teacherRepository.findAllByFirstName(firstName);
	}

	@Override
	public Set<TeacherEntity> getPersonsByLastName(String lastName) {
		return teacherRepository.findAllByLastName(lastName);
	}

	@Override
	public Set<TeacherEntity> getPersonsByFullName(String firstName, String lastName) {
		return teacherRepository.findAllByFirstNameAndLastName(firstName, lastName);
	}
}
