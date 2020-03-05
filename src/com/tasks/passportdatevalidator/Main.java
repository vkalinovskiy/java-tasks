package com.tasks.passportdatevalidator;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate birthDate = LocalDate.of(1975, 3, 1);
        LocalDate issuingPassportDate = LocalDate.of(2020, 3, 2);

        PassportDateValidator validator = new PassportDateValidator(birthDate, issuingPassportDate);

        System.out.println("Result: " + validator.isValid());
    }
}
