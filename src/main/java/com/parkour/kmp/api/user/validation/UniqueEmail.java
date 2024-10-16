package com.parkour.kmp.api.user.validation;

import com.parkour.kmp.api.user.validation.impl.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {

    String message() default "사용 중인 이메일입니다.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
