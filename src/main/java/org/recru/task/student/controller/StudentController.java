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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {
	private final StudentServiceFacade studentServiceFacade;

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public PersonEntity.ID createStudent(@RequestBody StudentCreate studentDto) {
		return studentServiceFacade.createPerson(studentDto);
	}

	@GetMapping("/page/")
	public Page<StudentRead> getStudentsByPage(Pageable page) {
		return studentServiceFacade.getPersonsPage(page);
	}

	@GetMapping("/teachers/{id}")
	public Set<TeacherRead> getTeachersOfStudent(@PathVariable("id") long studentId) {
		return studentServiceFacade.getTeachersOfStudent(studentId);
	}

	@GetMapping("/get")
	public Set<StudentRead> getAllStudentsByName(@RequestParam(value = "firstName", required = false) String firstName,
	                                             @RequestParam(value = "lastName", required = false) String lastName) {
		return studentServiceFacade.getPersonsByName(firstName, lastName);
	}

	@PutMapping("/update/")
	public void updateStudent(@RequestBody StudentUpdate studentDto) {
		studentServiceFacade.updatePerson(studentDto);
	}

	@PutMapping("/add/{studentId}/{teacherId}")
	public void addStudentToTeacher(@PathVariable("studentId") long studentId,
	                                @PathVariable("teacherId") long teacherId) {
		studentServiceFacade.addTeacherToStudent(studentId, teacherId);
	}

	@PutMapping("/remove/{studentId}/{teacherId}")
	public void removeStudentFromTeacher(@PathVariable("studentId") long studentId,
	                                     @PathVariable("teacherId") long teacherId) {
		studentServiceFacade.removeTeacherFromStudent(studentId, teacherId);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteStudent(@PathVariable("id") long id) {
		studentServiceFacade.deletePerson(id);
	}
}
