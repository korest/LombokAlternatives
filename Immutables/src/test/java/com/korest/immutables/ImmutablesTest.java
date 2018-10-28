package com.korest.immutables;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;

public class ImmutablesTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test(expected = UnsupportedOperationException.class)
    public void whenUserBuilderThenImmutableUserIsCreated() {
        final ImmutableUser immutableUser = createUser();

        assertEquals("User", immutableUser.getName());
        assertEquals("Immutables", immutableUser.getSurname());
        assertEquals(31, immutableUser.getAge());
        assertEquals(Arrays.asList("BMW", "Audi"), immutableUser.getCars());
        // UnsupportedOperationException should be thrown
        immutableUser.getCars().add("Ford");
    }

    @Test
    public void whenUserModifiedThenNewUserIsCreated() {
        final ImmutableUser user = createUser();
        final User modifiedUser = ImmutableUser.builder().from(user)
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
        final ImmutableUser immutableUser = ImmutableUser.builder()
                .name("User")
                .surname("Immutables")
                .age(31)
                .cars(Arrays.asList("BMW", "Audi"))
                .build();

        final String immutableUserJson = objectMapper.writeValueAsString(immutableUser);
        // InvalidDefinitionException should be thrown
        objectMapper.readValue(immutableUserJson, ImmutableUser.class);
    }

    @Test
    public void whenUserImmutablesJsonBuilderSerializeDeserializeThenUserJson() throws IOException {
        final ImmutableWork work = ImmutableWork.builder()
                .name("Work")
                .salary(BigDecimal.valueOf(2500))
                .build();

        final String workJson = objectMapper.writeValueAsString(work);
        final ImmutableWork workFromJson =
                objectMapper.readValue(workJson, ImmutableWork.class);

        assertEquals(work, workFromJson);
    }

    private ImmutableUser createUser() {
        return ImmutableUser.builder()
                .name("User")
                .surname("Immutables")
                .age(31)
                .cars(Arrays.asList("BMW", "Audi"))
                .build();
    }
}
