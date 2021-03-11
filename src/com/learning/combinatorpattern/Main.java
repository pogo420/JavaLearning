package com.learning.combinatorpattern;

import java.time.LocalDate;
import static com.learning.combinatorpattern.CustomerRegistrationValidator.*;

public class Main {

    public static void main(String[] args) {
        Customer customer = new Customer(
                "Sudeshna",
                "subqwer.com",
                "+917760684595",
                LocalDate.of(2010,02,1)
        );
        // executed top to bottom
        ValidationResult result = CustomerRegistrationValidator
                .isAdultValid()
                .and(isPhoneValid())
                .and(isEmailValid())
                .apply(customer);

        System.out.println(result);
    }


}
