package com.fareye.training.annotations.DuplicateTitle;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TitleValidator implements ConstraintValidator<DuplicateTitle, String> {
    public boolean isValid(String title, ConstraintValidatorContext cxt) {
        if (!titles.contains(title)) {
            titles.add(title);
            return true;
        }
        return false;

    }

    List<String> titles = new ArrayList<>();
}