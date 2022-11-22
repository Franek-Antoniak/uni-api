package org.recru.task.student.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StudentsIdValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StudentsIdConstraint {
	String message() default "Students id contains duplicates";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
