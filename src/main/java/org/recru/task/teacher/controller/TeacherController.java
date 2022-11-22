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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teachers")
@Validated
public class TeacherController {
	private final TeacherServiceFacade teacherServiceFacade;

	@PostMapping("/create")
	public PersonEntity.ID createTeacher(@RequestBody @Valid TeacherCreate studentDto) {
		return teacherServiceFacade.createPerson(studentDto);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteTeacher(@PathVariable("id") long id) {
		teacherServiceFacade.deletePerson(id);
	}

	@PutMapping("/update/")
	public void updateTeacher(@RequestBody @Valid TeacherUpdate studentDto) {
		teacherServiceFacade.updatePerson(studentDto);
	}

	@GetMapping("/page/")
	public Page<TeacherRead> getTeachersByPage(@RequestParam("page") Pageable page) {
		return teacherServiceFacade.getPersonsPage(page);
	}

	@GetMapping("/teachers/{id}")
	public Set<StudentRead> getStudentsOfTeacher(@PathVariable("id") long id) {
		return teacherServiceFacade.getStudentsOfTeacher(id);
	}
}
