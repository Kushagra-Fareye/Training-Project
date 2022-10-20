package com.fareye.training.annotations.DuplicateTitle;

import com.fareye.training.controller.TodoController;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TitleValidator implements ConstraintValidator<DuplicateTitle, String> {
    public boolean isValid(String title, ConstraintValidatorContext cxt) {
        if (!TodoController.titles.contains(title)) {
            TodoController.titles.add(title);
            return true;
        }
        return false;

    }

}