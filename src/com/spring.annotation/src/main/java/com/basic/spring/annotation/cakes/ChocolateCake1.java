package com.basic.spring.annotation.cakes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component  // creates the class as bean object
@Primary // if multiple bean of same type @Primary have advantage. IMP: @Qualifier has higher precedence than @Primary
public class ChocolateCake1 implements Cake {

    @Value("${chocolatecake1.base-flavour}") // initialize values from config file
    private Flavours baseFlavour;

    @Value("${chocolatecake1.secondary-flavour}") // initialize values from config file
    private Flavours secondaryFlavour;

    @Value("${chocolatecake1.sprinkle-flavour}") // initialize values from config file
    private Flavours sprinkleFlavour;


    @Override
    public String makeCake(Flavours baseFlavour, Flavours secondaryFlavour, Flavours sprinkleFlavour) {
        return "cake with base:"+baseFlavour+", secondary flavour:"+secondaryFlavour+" and topping:"+sprinkleFlavour;
    }

    @Override
    public String getCake() {
        return makeCake(baseFlavour, secondaryFlavour, sprinkleFlavour) ;
    }
}
