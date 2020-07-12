package com.stivenaguino.util;

import org.primefaces.PrimeFaces;

public class Utilities {

    public static <T> T nvl(T value, T alternateValue) {
        return (value == null) ? alternateValue : value;
    }

    public static void resetComponent(String component) {
        PrimeFaces.current().resetInputs(component);
    }

}
