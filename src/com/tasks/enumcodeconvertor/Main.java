package com.tasks.enumcodeconvertor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {
    public static void main(String[] args) {
        CodedEnumConvertor<ExampleEnum> convertor = new CodedEnumConvertor<>(ExampleEnum.class, Enum::ordinal);
//        convertor.fromCode(1);
    }
}
