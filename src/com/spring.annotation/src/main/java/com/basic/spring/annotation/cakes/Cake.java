package com.basic.spring.annotation.cakes;

enum Flavours{
    CHOCOLATE,
    VANILLA,
    STRAWBERRY,
    COCONUT
}

public interface Cake {
     public String makeCake(Flavours baseFlavour, Flavours secondaryFlavour, Flavours sprinkleFlavour);
     public String getCake();
}
