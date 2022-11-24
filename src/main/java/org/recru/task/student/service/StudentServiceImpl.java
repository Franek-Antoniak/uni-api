package org.recru.task.student.service;

import lombok.RequiredArgsConstructor;
import org.recru.task.student.StudentEntity;
import org.recru.task.student.exception.StudentNotFoundException;
import org.recru.task.student.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
class StudentServiceImpl extends StudentService {
	private final StudentRepository studentRepository;

	@Override
	public StudentEntity savePerson(StudentEntity personEntity) {
		return studentRepository.save(personEntity);
	}


	@Override
	@Transactional
	public void deletePerson(Long studentId) {
		StudentEntity studentEntity = studentRepository.findById(studentId)
		                                               .orElseThrow(() -> new StudentNotFoundException(studentId));
		studentEntity.getTeachers()
		             .forEach(teacherEntity -> teacherEntity.getStudents()
		                                                    .remove(studentEntity));
		studentRepository.deleteById(studentId);
	}

	@Override
	public StudentEntity getPerson(Long studentId) {
		return studentRepository.findById(studentId)
		                        .orElseThrow(() -> new StudentNotFoundException(studentId));
	}

	@Override
	public Page<StudentEntity> getPersonsPage(Pageable pageable) {
		return studentRepository.findAll(pageable);
	}


	@Override
	public Set<StudentEntity> getPersonsById(List<Long> studentsId) {
		return studentRepository.getAllByPersonIdIn(studentsId);
	}

	@Override
	public Set<StudentEntity> getPersonsByFirstName(String firstName) {
		return studentRepository.findAllByFirstName(firstName);
	}

	@Override
	public Set<StudentEntity> getPersonsByLastName(String lastName) {
		return studentRepository.findAllByLastName(lastName);
	}

	@Override
	public Set<StudentEntity> getPersonsByFullName(String firstName, String lastName) {
		return studentRepository.findAllByFirstNameAndLastName(firstName, lastName);
	}
}
