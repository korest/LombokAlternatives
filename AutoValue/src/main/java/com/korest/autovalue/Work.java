package com.korest.autovalue;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

import java.math.BigDecimal;

@AutoValue
@JsonDeserialize(builder = AutoValue_Work.Builder.class)
public abstract class Work {

    @JsonProperty("name")
    public abstract String getName();

    @JsonProperty("salary")
    public abstract BigDecimal getSalary();

    public static Builder builder() {
        return new AutoValue_Work.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {

        @JsonProperty("name")
        public abstract Builder setName(String name);

        @JsonProperty("salary")
        public abstract Builder setSalary(BigDecimal salary);

        public abstract Work build();
    }
}
