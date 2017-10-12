package io.mbhaphoenix.partitionconverter.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PartitionDefinitionValidator.class)
public @interface ValidPartitionDefinition {
    String message() default "Invalid partition definition";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
