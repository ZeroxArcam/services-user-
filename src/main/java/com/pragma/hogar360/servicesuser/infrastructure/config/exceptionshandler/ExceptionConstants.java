package com.pragma.hogar360.servicesuser.infrastructure.config.exceptionshandler;

public class ExceptionConstants {
    private ExceptionConstants(){}

    public static final String ROLE_NOT_FOUND = "Role Not Found";
    public static final String EMAIL_NOT_FOUND = "Email not found";

    public static final String INVALID_EMAIL = "Invalid email";
    public static final String INVALID_PHONE_NUMBER =  "Invalid phone number format. Please use only numeric digits. The phone number cannot be empty or exceed 13 digits.";
    public static final String INVALID_ID_NUMBER = "Invalid id number";
    public static final String INVALID_AGE = "Invalid age";
    public static final String INVALID_DATE_OF_BIRTH = "Invalid date of birth";
    public static final String INVALID_DATE_FORMAT ="Invalid date format. Please enter a valid date with 'yyyy-MM-dd' format, and 4-digit year.";

    public static final String FIELD_NAME_NULL_MESSAGE = "Name cannot be null.";
    public static final String FIELD_NAME_MAX_LENGTH_MESSAGE = "Name cannot exceed 50 characters.";
    public static final String FIELD_NAME_EMPTY_MESSAGE = "Name cannot be empty.";
    public static final String FIELD_LAST_NAME_EMPTY_MESSAGE = "Last name cannot be empty.";
    public static final String FIELD_DESCRIPTION_EMPTY_MESSAGE = "Description cannot be empty.";
    public static final String FIELD_LAST_NAME_MAX_LENGTH_MESSAGE = "Last name cannot exceed 50 characters.";

    public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
    public static final String PHONE_NUMBER_ALREADY_EXISTS = "Phone number already exists";
    public static final String ID_NUMBER_ALREADY_EXISTS = "Id number already exists";
    public static final String USER_ALREADY_EXISTS = "User already exists";
    public static final String ID_ALREADY_EXISTS = "Id already exists";
    public static final String ROLE_ALREADY_EXISTS = "Role already exists";

}
