package org.recru.task.student.controller;

import lombok.RequiredArgsConstructor;
import org.recru.task.person.PersonEntity;
import org.recru.task.student.dto.StudentCreate;
import org.recru.task.student.dto.StudentRead;
import org.recru.task.student.dto.StudentUpdate;
import org.recru.task.student.facade.StudentServiceFacade;
import org.recru.task.teacher.dto.TeacherRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
@Validated
public class StudentController {
	private final StudentServiceFacade studentServiceFacade;

	@PostMapping("/create")
	public PersonEntity.ID createStudent(@RequestBody @Valid StudentCreate studentDto) {
		return studentServiceFacade.createPerson(studentDto);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteStudent(@PathVariable("id") long id) {
		studentServiceFacade.deletePerson(id);
	}

	@PutMapping("/update/")
	public void updateStudent(@RequestBody @Valid StudentUpdate studentDto) {
		studentServiceFacade.updatePerson(studentDto);
	}

	@GetMapping("/page/")
	public Page<StudentRead> getStudentsByPage(@RequestParam("page") Pageable page) {
		return studentServiceFacade.getPersonsPage(page);
	}

	@GetMapping("/teachers/{id}")
	public Set<TeacherRead> getTeachersOfStudent(@PathVariable("id") long id) {
		return studentServiceFacade.getTeachersOfStudent(id);
	}

	@PutMapping("/teachers/add/{studentId}/{teacherId}")
	public void addTeacherToStudent(@PathVariable("studentId") long studentId,
	                                @PathVariable("teacherId") long teacherId) {
		studentServiceFacade.addTeacherToStudent(studentId, teacherId);
	}

	@PutMapping("/teachers/remove/{studentId}/{teacherId}")
	public void removeTeacherFromStudent(@PathVariable("studentId") long studentId,
	                                     @PathVariable("teacherId") long teacherId) {
		studentServiceFacade.removeTeacherFromStudent(studentId, teacherId);
	}
}
