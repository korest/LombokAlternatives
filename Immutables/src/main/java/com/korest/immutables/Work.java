package com.korest.immutables;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import java.math.BigDecimal;

@Value.Immutable
@JsonDeserialize(builder = ImmutableWork.Builder.class)
public abstract class Work {

    public abstract String name();

    public abstract BigDecimal salary();

}
