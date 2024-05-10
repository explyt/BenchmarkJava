package org.owasp.benchmark.helpers;

public class ArrayHolder {
    private String[] values;

    public ArrayHolder(String value) {
        this(new String[] {value, "A", "B"}); // Динамическая инициализация
    }

    public ArrayHolder(String[] initialValues) {
        this.values = initialValues;
    }

    public String[] getValues() {
        return values;
    }
}