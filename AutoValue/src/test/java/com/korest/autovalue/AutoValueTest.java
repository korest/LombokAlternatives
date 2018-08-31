package com.korest.autovalue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;

public class AutoValueTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test(expected = UnsupportedOperationException.class)
    public void whenUserBuilderThenUserIsCreated() {
        final User user = createUser();

        assertEquals("User", user.getName());
        assertEquals("AutoValue", user.getSurname());
        assertEquals(24, user.getAge());
        assertEquals(Arrays.asList("Dodge"), user.getCars());
        // UnsupportedOperationException should be thrown
        user.getCars().add("Ford");
    }

    @Test
    public void whenUserModifiedThenNewUserIsCreated() {
        final User user = createUser();
        final User modifiedUser = user.toBuilder()
                .setName("Modified User")
                .build();

        assertNotSame(user, modifiedUser);
        assertNotEquals(user.getName(), modifiedUser.getName());
        assertEquals(user.getSurname(), modifiedUser.getSurname());
        assertEquals(user.getAge(), modifiedUser.getAge());
        assertEquals(user.getCars(), modifiedUser.getCars());
    }

    @Test(expected = InvalidDefinitionException.class)
    public void whenUserBuilderSerializeDeserializeThenException() throws IOException {
        final User user = User.builder()
                .setName("User")
                .setSurname("AutoValue")
                .setAge(24)
                .setCars(Arrays.asList("Dodge"))
                .build();

        final String userJson = objectMapper.writeValueAsString(user);

        // InvalidDefinitionException should be thrown as default implementation is not deserializable
        objectMapper.readValue(userJson, User.class);
    }

    @Test
    public void whenWorkBuilderSerializeDeserializeThenWork() throws IOException {
        final Work work = Work.builder()
                .setName("Work")
                .setSalary(BigDecimal.valueOf(1000))
                .build();

        final String workJson = objectMapper.writeValueAsString(work);
        final Work workFromJson =
                objectMapper.readValue(workJson, Work.class);

        assertEquals(work, workFromJson);
    }

    private User createUser() {
        return User.builder()
                .setName("User")
                .setSurname("AutoValue")
                .setAge(24)
                .setCars(Arrays.asList("Dodge"))
                .build();
    }

}
