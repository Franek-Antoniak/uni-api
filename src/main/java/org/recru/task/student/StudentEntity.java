package org.recru.task.student;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.recru.task.major.UniMajor;
import org.recru.task.person.PersonEntity;
import org.recru.task.teacher.TeacherEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "student")
public class StudentEntity extends PersonEntity {
	private UniMajor major;
	@ManyToMany(mappedBy = "students")
	private Set<TeacherEntity> teachers;

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
		StudentEntity that = (StudentEntity) o;
		return getPersonId() != null && Objects.equals(getPersonId(), that.getPersonId());
	}
}
