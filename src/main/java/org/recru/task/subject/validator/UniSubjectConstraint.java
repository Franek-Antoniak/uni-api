package org.recru.task.subject.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniSubjectValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniSubjectConstraint {
	String message() default "Invalid subject or subject is not supported";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
