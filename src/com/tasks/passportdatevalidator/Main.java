package com.tasks.passportdatevalidator;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate birthDate = LocalDate.of(1997, 2, 1);
        LocalDate issuingPassportDate = LocalDate.of(2017, 8, 10);

        PassportDateValidator validator = new PassportDateValidator(birthDate, issuingPassportDate);

        System.out.println("Result: " + validator.isValid());
    }
}
