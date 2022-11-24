package org.recru.task.person.validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.constraints.Email;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailConstraint.EmailValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Email(
		message = "Email should be valid",
		regexp = "^([A-Za-z0-9])+([\\-|_|\\.]?)([A-Za-z0-9])+@([A-Za-z0-9]*\\" + ".?[A-Za-z]*\\.[A-Za-z]{2,4})$"
)
public @interface EmailConstraint {
	String message() default "Email is already taken";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	class EmailValidator implements ConstraintValidator<EmailConstraint, String> {
		@PersistenceContext
		private EntityManager entityManager;

		@Override
		public boolean isValid(String value, ConstraintValidatorContext context) {
			if (value == null) {
				return true;
			}
			return entityManager.createQuery("SELECT 1 FROM PersonEntity p WHERE p.email = " + ":email")
			                    .setParameter("email", value)
			                    .getResultList()
			                    .isEmpty();
		}
	}
}
