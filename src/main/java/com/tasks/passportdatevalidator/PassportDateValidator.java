package com.tasks.passportdatevalidator;

import java.time.LocalDate;
import java.time.Period;

public class PassportDateValidator {
    protected LocalDate issuingPassportDate;
    protected LocalDate birthDateByNewPassport;

    PassportDateValidator(LocalDate birthDate, LocalDate issuingPassportDate) {
        this.issuingPassportDate = issuingPassportDate;

        int age = this.getAgeByBirthDate(birthDate);
        int ageForNewPassport = this.getAgeForNewPassport(age);

        this.birthDateByNewPassport = this.getDateForIssuingNewPassport(birthDate, ageForNewPassport);
    }

    protected int getAgeByBirthDate(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();

        return Period.between(birthDate, currentDate).getYears();
    }

    protected int getAgeForNewPassport(int age) {
        int nextAge;

        if(age <= 14) {
            nextAge = 14;
        } else if(age <= 21) {
            nextAge = 21;
        } else {
            nextAge = 45;
        }

        return nextAge;
    }

    protected LocalDate getDateForIssuingNewPassport(LocalDate birthDate, int age) {
        return  birthDate.plusYears(age);
    }

    public boolean isValid() {
        return this.issuingPassportDate.isAfter(this.birthDateByNewPassport);
    }
}
