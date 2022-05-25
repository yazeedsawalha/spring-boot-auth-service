package com.auth.authservice.util;

public class NumberUtil {

    public static Integer toIntegerOrElseZero(final String value) {
        try {
            return Integer.valueOf(value);
        } catch (Exception e) {
            return 0;
        }
    }

    public static Integer toIntegerOrElseNull(final String value) {
        try {
            return Integer.valueOf(value);
        } catch (Exception e) {
            return null;
        }
    }

    public static String valueOf(final Integer value) {
        if (value == null) {
            return null;
        }
        return String.valueOf(value);
    }
}
