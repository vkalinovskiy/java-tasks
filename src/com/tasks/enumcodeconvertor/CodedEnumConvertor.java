package com.tasks.enumcodeconvertor;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CodedEnumConvertor<T extends Enum<T>> {
    protected Map<Integer, T> map;
    private Function<T, Integer> codeGetter;

    public CodedEnumConvertor(Class<T> tClass, Function<T, Integer> codeGetter) {
        this.codeGetter = codeGetter;
        this.map = Stream.of(tClass.getEnumConstants())
                .collect(Collectors.toMap(this::toCode, i -> i));
    }

    public Enum<T> fromCode(Integer code) {
        return map.get(code);
    }

    public Integer toCode(Enum<T> element) {
        return codeGetter.apply((T) element);
    }
}
