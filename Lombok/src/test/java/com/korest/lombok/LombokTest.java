package com.korest.lombok;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;

public class LombokTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test(expected = UnsupportedOperationException.class)
    public void whenUserBuilderThenUserIsCreated() {
        final User user = createUser();

        assertEquals("User", user.getName());
        assertEquals("Lombok", user.getSurname());
        assertEquals(27, user.getAge());
        assertEquals(Arrays.asList("VW"), user.getCars());
        // UnsupportedOperationException should be thrown
        user.getCars().add("Ford");
    }

    @Test
    public void whenUserModifiedThenNewUserIsCreated() {
        final User user = createUser();
        final User modifiedUser = user.toBuilder()
                .name("Modified User")
                .build();

        assertNotSame(user, modifiedUser);
        assertNotEquals(user.getName(), modifiedUser.getName());
        assertEquals(user.getSurname(), modifiedUser.getSurname());
        assertEquals(user.getAge(), modifiedUser.getAge());
        assertEquals(user.getCars(), modifiedUser.getCars());
    }

    @Test(expected = InvalidDefinitionException.class)
    public void whenUserBuilderSerializeDeserializeThenException() throws IOException {
        final User user = createUser();

        final String userJson = objectMapper.writeValueAsString(user);
        // InvalidDefinitionException should be thrown
        objectMapper.readValue(userJson, User.class);
    }

    @Test
    public void whenUserJsonBuilderSerializeDeserializeThenUser() throws IOException {
        final Work work = Work.builder()
                .name("Work")
                .salary(BigDecimal.valueOf(2000))
                .build();

        final String workJson = objectMapper.writeValueAsString(work);
        final Work workFromJson =
                objectMapper.readValue(workJson, Work.class);

        assertEquals(work, workFromJson);
    }

    private User createUser() {
        return User.builder()
                .name("User")
                .surname("Lombok")
                .age(27)
                .cars(Arrays.asList("VW"))
                .build();
    }
}
