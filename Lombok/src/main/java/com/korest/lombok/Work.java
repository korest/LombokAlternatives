package com.korest.lombok;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
@JsonDeserialize(builder = Work.WorkBuilder.class)
public class Work {

    @NonNull
    String name;
    @NonNull
    BigDecimal salary;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class WorkBuilder {
    }
}
