package com.tasks.enumcodeconvertor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodedEnumConvertorTest {

    @Test
    void fromCode() {
        CodedEnumConvertor<ExampleEnum> convertor = new CodedEnumConvertor<>(ExampleEnum.class, ExampleEnum::getCode);

        assertEquals(ExampleEnum.A, convertor.fromCode(5));
     }

    @Test
    void toCode() {
        CodedEnumConvertor<ExampleEnum> convertor =
                new CodedEnumConvertor<>(ExampleEnum.class, ExampleEnum::getCode);

        assertEquals(5, convertor.toCode(ExampleEnum.A));
    }
}
