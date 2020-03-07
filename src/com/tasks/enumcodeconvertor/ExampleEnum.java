package com.tasks.enumcodeconvertor;

public enum ExampleEnum {
    A(5),
    B(2),
    C(3);

    private Integer code;

    ExampleEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
