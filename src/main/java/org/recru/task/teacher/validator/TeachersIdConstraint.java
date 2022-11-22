package org.recru.task.teacher.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TeacherIdValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TeachersIdConstraint {
	String message() default "Teachers id contains duplicates";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}