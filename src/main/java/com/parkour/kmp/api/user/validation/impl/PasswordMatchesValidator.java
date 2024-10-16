package com.parkour.kmp.api.user.validation.impl;

import com.parkour.kmp.api.user.payload.request.UserSignUpRequest;
import com.parkour.kmp.api.user.validation.PasswordMatches;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserSignUpRequest> {

    @Override
    public boolean isValid(UserSignUpRequest userSignUpDto, ConstraintValidatorContext context) {
        return userSignUpDto.password() != null
                && userSignUpDto.password().equals(userSignUpDto.checkPassword());
    }
}