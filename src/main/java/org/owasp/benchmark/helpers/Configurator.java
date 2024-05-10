package org.owasp.benchmark.helpers;

public class Configurator {
    private String configurationValue;

    public Configurator(int value) {
        this(value, "EMPTY"); // Значение по умолчанию
    }

    public Configurator(int value, String configurationValue) {
        this.configurationValue = value > 50 ? "EMPTY" : configurationValue;
    }

    public String getConfigurationValue() {
        return configurationValue;
    }
}