package com.learning.autovalue;

import com.google.auto.value.AutoValue;
import com.learning.autovalue.AutoValue_AutoCheck;

@AutoValue
public abstract class AutoCheck {

    public abstract String getName();
    public abstract int getId();

    @AutoValue.Builder
    public static abstract class Builder{
        public abstract Builder setName(String val);
        public abstract Builder setId(int val);
        public abstract AutoCheck build();
    }

    public static Builder builder(){
        return new AutoValue_AutoCheck.Builder();
    }

}
