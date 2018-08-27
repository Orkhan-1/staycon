package com.staycon.validation;

import com.staycon.models.PrincipalModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator <PasswordMatch, PrincipalModel> {

    @Override
    public void initialize(PasswordMatch passwordMatch) {

    }

    @Override
    public boolean isValid(PrincipalModel principalModel, ConstraintValidatorContext constraintValidatorContext) {

        String plainPassword = principalModel.getPlainPassword();
        String repeatPassword = principalModel.getRepeatPassword();

        if (plainPassword==null || plainPassword.length()==0) {
            return true;
        }

        if (!plainPassword.equals(repeatPassword)) {
            return false;
        }

        return true;
    }
}
