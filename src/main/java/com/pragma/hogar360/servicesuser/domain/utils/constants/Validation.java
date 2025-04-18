package com.pragma.hogar360.servicesuser.domain.utils.constants;

import com.pragma.hogar360.servicesuser.domain.exceptions.*;

import java.util.Objects;
import java.util.regex.Pattern;

public class Validation {
    private Validation(){}

    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final String PHONE_REGEX = "^(\\+\\d{1,3})?\\d{10,12}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);


    public static boolean isValidEmail(String email) {
        if (email == null || email.isBlank()) {
            System.out.println("Email nulo o vacío");
            return false;
        }
        boolean result = EMAIL_PATTERN.matcher(email.trim()).matches();
        System.out.println("Validando email: " + email + " -> " + result);
        return result;
    }
    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.isBlank()) {
            System.out.println("Teléfono nulo o vacío");
            return false;
        }
        boolean result = PHONE_PATTERN.matcher(phone.trim()).matches();
        System.out.println("Validando teléfono: " + phone + " -> " + result);
        return result;
    }
    public static void validateName(String name) {
        Objects.requireNonNull(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
        if (name.length() > DomainConstants.FIELD_NAME_MAX_LENGTH) {
            throw new NameMaxSizeExceededException(DomainConstants.FIELD_NAME_MAX_LENGTH_MESSAGE);
        }
        if (name.isBlank()) {
            throw new EmptyNameException(DomainConstants.FIELD_NAME_EMPTY_MESSAGE);
        }
    }

    public static void validateLastName(String name) {
        Objects.requireNonNull(name, DomainConstants.FIELD_LAST_NAME_EMPTY_MESSAGE);
        if (name.length() > DomainConstants.FIELD_NAME_MAX_LENGTH) {
            throw new LastNameMaxSizeExceededException(DomainConstants.FIELD_LAST_NAME_MAX_LENGTH_MESSAGE);
        }
        if (name.isBlank()) {
            throw new EmptyLastNameException(DomainConstants.FIELD_LAST_NAME_MAX_LENGTH_MESSAGE);
        }
    }


    public static void validateDescription(String description) {
        Objects.requireNonNull(description, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
        if (description.length() > DomainConstants.FIELD_DESCRIPTION_MAX_LENGTH) {
            throw new DescriptionMaxSizeExceededException(DomainConstants.FIELD_DESCRIPTION_MAX_LENGTH_MESSAGE);
        }
        if (description.isBlank()) {
            throw new EmptyDescriptionException(DomainConstants.FIELD_DESCRIPTION_EMPTY_MESSAGE);
        }
    }

}
