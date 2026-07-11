package com.codingshuttle.shayan.module2restApi.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = EmployeeRoleValidator.class)
//Now whenever we called this validation annotation it will use EmployeeRoleValidator class to validate the Employee Role
public @interface EmployeeRoleValidation {  //@interface is used to define a custom annotation

    String message() default "Role of Employee Either be ADMIN or USER";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
