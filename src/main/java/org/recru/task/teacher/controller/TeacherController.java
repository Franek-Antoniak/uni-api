package org.recru.task.teacher.controller;

import lombok.RequiredArgsConstructor;
import org.recru.task.person.PersonEntity;
import org.recru.task.student.dto.StudentRead;
import org.recru.task.teacher.dto.TeacherCreate;
import org.recru.task.teacher.dto.TeacherRead;
import org.recru.task.teacher.dto.TeacherUpdate;
import org.recru.task.teacher.facade.TeacherServiceFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teachers")
public class TeacherController {
	private final TeacherServiceFacade teacherServiceFacade;

	@PostMapping("/create")
	public PersonEntity.ID createTeacher(@RequestBody TeacherCreate studentDto) {
		return teacherServiceFacade.createPerson(studentDto);
	}

	@GetMapping("/page")
	public Page<TeacherRead> getTeachersByPage(Pageable page) {
		return teacherServiceFacade.getPersonsPage(page);
	}

	@GetMapping("/teachers/{id}")
	public Set<StudentRead> getStudentsOfTeacher(@PathVariable("id") long teacherId) {
		return teacherServiceFacade.getStudentsOfTeacher(teacherId);
	}

	@GetMapping("/get")
	public Set<TeacherRead> getAllTeachersByName(@RequestParam(value = "firstName", required = false) String firstName,
	                                             @RequestParam(value = "lastName", required = false) String lastName) {
		return teacherServiceFacade.getPersonsByName(firstName, lastName);
	}

	@PutMapping("/update/")
	public void updateTeacher(@RequestBody TeacherUpdate studentDto) {
		teacherServiceFacade.updatePerson(studentDto);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteTeacher(@PathVariable("id") long id) {
		teacherServiceFacade.deletePerson(id);
	}
}
