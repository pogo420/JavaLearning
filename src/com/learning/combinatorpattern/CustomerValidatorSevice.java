package com.learning.combinatorpattern;

import java.time.LocalDate;
import java.time.Period;

public class CustomerValidatorSevice {

    public boolean isEmailValid(String email){
        return email.contains("@");
    }

    public boolean isPhoneValid(String email){
        return email.startsWith("+91");
    }

    public boolean isAgeValid(LocalDate dop){
        return Period.between(dop, LocalDate.now()).getYears() > 16;
    }
}
