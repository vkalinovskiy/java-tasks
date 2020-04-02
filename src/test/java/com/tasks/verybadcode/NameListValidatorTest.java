package com.tasks.verybadcode;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NameListValidatorTest {

    @Test
    @SneakyThrows
    void validate() {
        var nameList = List.of("Саша", "Маша", "Паша");
        var validator = new NameListValidator();

        Boolean isValid = validator.validate(nameList);

        assertEquals(false, isValid);

        var namesString = new String(Files.readAllBytes(Path.of(validator.foundNameListFilePath)));

        assertEquals("Саша;Маша", namesString);
    }
}