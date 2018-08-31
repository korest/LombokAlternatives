package com.korest.lombok;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
@JsonDeserialize(builder = Work.WorkBuilder.class)
public class Work {

    String name;
    BigDecimal salary;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class WorkBuilder {
    }
}
