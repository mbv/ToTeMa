package edu.bsuir.totema.util;

import java.sql.Date;
import java.util.HashMap;

public final class ValidationUtil {
    private final static String messageFieldIsEmpty = "Attribute \"%s\" is empty";
    private final static String messageFieldIsNotValidLong = "Attribute \"%s\" is not valid long";
    private final static String messageFieldIsNotValidInt = "Attribute \"%s\" is not valid integer";
    private final static String messageFieldIsNotValidDate = "Attribute \"%s\" is not valid date";

    public static void validateStringOnEmpty(HashMap<String, String> attributes, String fieldName, HashMap<String, String> errors) {
        if (!attributes.containsKey(fieldName) || attributes.get(fieldName).trim().isEmpty()) {
            errors.put(fieldName, String.format(messageFieldIsEmpty, fieldName));
        }
    }

    public static void validateLong(HashMap<String, String> attributes, String fieldName, HashMap<String, String> errors) {
        if (!attributes.containsKey(fieldName)) {
            errors.put(fieldName, String.format(messageFieldIsEmpty, fieldName));
        } else {
            try {
                Long.parseLong(attributes.get(fieldName));
            }
            catch (NumberFormatException e) {
                errors.put(fieldName, String.format(messageFieldIsNotValidLong, fieldName));
            }
        }
    }

    public static void validateInt(HashMap<String, String> attributes, String fieldName, HashMap<String, String> errors) {
        if (!attributes.containsKey(fieldName)) {
            errors.put(fieldName, String.format(messageFieldIsEmpty, fieldName));
        } else {
            try {
                Integer.parseInt(attributes.get(fieldName));
            }
            catch (NumberFormatException e) {
                errors.put(fieldName, String.format(messageFieldIsNotValidInt, fieldName));
            }
        }
    }

    public static void validateDate(HashMap<String, String> attributes, String fieldName, HashMap<String, String> errors) {
        if (!attributes.containsKey(fieldName)) {
            errors.put(fieldName, String.format(messageFieldIsEmpty, fieldName));
        } else {
            try {
                Date.valueOf(attributes.get(fieldName));
            }
            catch (IllegalArgumentException e) {
                errors.put(fieldName, String.format(messageFieldIsNotValidDate, fieldName));
            }
        }
    }
}
