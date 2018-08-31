package com.korest.autovalue;

import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class User {

    public abstract String getName();

    public abstract String getSurname();

    public abstract int getAge();

    public abstract List<String> getCars();

    // first option to create an instance
//    public static User create(final String name, final String surname, final int age, final List<String> cars) {
//        return new AutoValue_User(name, surname, age, cars);
//    }

    public static Builder builder() {
        return new AutoValue_User.Builder();
    }

    public abstract Builder toBuilder();

    // second option to create instance via builder
    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setName(String name);

        public abstract Builder setSurname(String surname);

        public abstract Builder setAge(int age);

        public abstract Builder setCars(List<String> cars);

        public abstract User build();
    }
}
