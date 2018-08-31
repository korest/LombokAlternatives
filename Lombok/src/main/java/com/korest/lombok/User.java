package com.korest.lombok;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class User {

    String name;
    String surname;
    int age;
    List<String> cars;

}
