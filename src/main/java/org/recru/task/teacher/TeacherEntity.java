package org.recru.task.teacher;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.recru.task.person.PersonEntity;
import org.recru.task.student.StudentEntity;
import org.recru.task.subject.UniSubject;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "teacher")
public class TeacherEntity extends PersonEntity {
	private UniSubject subject;
	@ManyToMany
	@JoinTable(
			name = "teacher_student", joinColumns = @JoinColumn(name = "teacher_id"),
			inverseJoinColumns = @JoinColumn(name = "student_id")
	)
	private Set<StudentEntity> students;

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		TeacherEntity that = (TeacherEntity) o;
		return getPersonId() != null && Objects.equals(getPersonId(), that.getPersonId());
	}
}
