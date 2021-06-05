package com.basic.spring.annotation;

import com.basic.spring.annotation.cakes.Cake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component  // create an bean object of a class
public class CakeMaker {

    @Autowired // injects some other beans, it must be under component.
    @Qualifier("coconutCake") // If multiple beans of same type, it will select based on the name.
    private Cake cake;

    public String getCake() {
        return cake.getCake();
    }

}