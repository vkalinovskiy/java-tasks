package com.tasks.verybadcode;

import lombok.SneakyThrows;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class NameListValidator {

    protected final String blackListFilePath = "src/main/java/com/tasks/verybadcode/storage/black-list.txt";
    protected final String foundNameListFilePath = "src/main/java/com/tasks/verybadcode/storage//found-name-list.txt";

    public Boolean validate(List<String> nameList) {
        var namesOfBlackList = getNamesOfBlackList();
        var foundNames = findMatches(namesOfBlackList, nameList);
        var isValid = foundNames.isEmpty();

        if (!isValid) {
            writeFoundNamesToFile(foundNames);
        }

        return isValid;
    }

    @SneakyThrows
    protected List<String> getNamesOfBlackList() {
        Pattern pattern = Pattern.compile(";", Pattern.UNICODE_CHARACTER_CLASS);

        return Files.lines(Paths.get(blackListFilePath))
                .flatMap(pattern::splitAsStream)
                .collect(Collectors.toList());
    }

    protected List<String> findMatches(List<String> baseList, List<String> desiredValueList) {
        return baseList.stream()
                .filter(base -> desiredValueList.stream()
                        .anyMatch(desired -> base.equals(desired)))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    protected void writeFoundNamesToFile(List<String> nameList) {
        String namesString = String.join(";", nameList);
        Files.writeString(Paths.get(foundNameListFilePath), namesString, StandardCharsets.UTF_8);
    }
}
