package com.tasks.passportdatevalidator;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;



class PassportDateValidatorTest {
    @Test
    void isValid_IssuingDateGreaterThanBirthDate_True() {
        LocalDate birthDate = LocalDate.of(2000, 2, 1);
        LocalDate issuingPassportDate = LocalDate.of(2021, 3, 1);

        PassportDateValidator validator = new PassportDateValidator(birthDate, issuingPassportDate);

        assertTrue(validator.isValid());
    }

    @Test
    void isValid_IssuingDateLessThanBirthDate_False() {
        LocalDate birthDate = LocalDate.of(2000, 2, 1);
        LocalDate issuingPassportDate = LocalDate.of(2015, 3, 2);

        PassportDateValidator validator = new PassportDateValidator(birthDate, issuingPassportDate);

        assertFalse(validator.isValid());
    }
}