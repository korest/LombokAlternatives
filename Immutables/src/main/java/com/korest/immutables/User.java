package com.korest.immutables;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public abstract class User {

    public abstract String getName();

    public abstract String getSurname();

    public abstract int getAge();

    public abstract List<String> getCars();

}
