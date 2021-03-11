package com.learning.combinatorpattern;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;
import  static com.learning.combinatorpattern.CustomerRegistrationValidator.*;

public interface CustomerRegistrationValidator
        extends Function<Customer, ValidationResult> {

    enum ValidationResult {
        SUCCESS,
        PHONE_NOT_VAL,
        EMAIL_NOT_VAL,
        NOT_ADULT
    }
    static  CustomerRegistrationValidator isEmailValid(){
        return customer -> customer.getEmail().contains("@") ?
                ValidationResult.SUCCESS : ValidationResult.EMAIL_NOT_VAL;
    }

    static  CustomerRegistrationValidator isPhoneValid(){
        return customer -> customer.getPhoneNumber().startsWith("+91") ?
                ValidationResult.SUCCESS : ValidationResult.PHONE_NOT_VAL;
    }

    static  CustomerRegistrationValidator isAdultValid(){
        return customer -> Period.between(customer.getDob(), LocalDate.now()).getYears() > 16 ?
                ValidationResult.SUCCESS : ValidationResult.NOT_ADULT;
    }

    default CustomerRegistrationValidator and(CustomerRegistrationValidator other){
        return customer -> {
            ValidationResult result = this.apply(customer);
            return result.equals(ValidationResult.SUCCESS)? other.apply(customer): result;
        };
    }
}
