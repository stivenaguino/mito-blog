package com.stivenaguino.util;

public class Utilities {

    public static <T> T nvl(T value, T alternateValue) {
        return (value == null) ? alternateValue : value;
    }

}
