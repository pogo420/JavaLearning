package com.basic.spring.annotation.cakes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component  // creates the class as bean object
public class CoconutCake implements Cake {

    @Value("${coconutcake.base-flavour}") // initialize values from config file
    private Flavours baseFlavour;

    @Value("${coconutcake.secondary-flavour}") // initialize values from config file
    private Flavours secondaryFlavour;

    @Value("${coconutcake.sprinkle-flavour}") // initialize values from config file
    private Flavours sprinkleFlavour;

    @Override
    public String makeCake(Flavours baseFlavour, Flavours secondaryFlavour, Flavours sprinkleFlavour) {
        return "cake with base:"+baseFlavour+", secondary flavour:"+secondaryFlavour+" and topping:"+sprinkleFlavour;
    }

    @Override
    public String getCake() {
        return makeCake(baseFlavour, secondaryFlavour, sprinkleFlavour);
    }
}
