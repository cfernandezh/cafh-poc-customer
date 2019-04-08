package com.cafh.poc.customer.util;

import com.cafh.poc.customer.controller.CustomerRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.TimeZone;

public class ValidAgeValidator implements ConstraintValidator<ValidAge, CustomerRequest> {

    @Override
    public void initialize(ValidAge validAge) {
    }

    @Override
    public boolean isValid(CustomerRequest value, ConstraintValidatorContext context) {

        LocalDateTime birthdayDate = LocalDateTime.now();
        try {
            birthdayDate = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(
                            new SimpleDateFormat("dd/MM/yyyy").parse(value.getDateOfBirth()).getTime())
                    , TimeZone.getDefault().toZoneId());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(birthdayDate.getYear(),
                birthdayDate.getMonthValue(),
                birthdayDate.getDayOfMonth());
        Period period = Period.between(birthday, today);

        if(value.getAge().compareTo(period.getYears()) != 0) {
            return false;
        }

        return true;
    }
}
