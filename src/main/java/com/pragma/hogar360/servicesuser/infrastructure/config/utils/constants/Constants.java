package com.pragma.hogar360.servicesuser.infrastructure.config.utils.constants;

public final class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String DESCRIPTION_ENDPOINT = "Operations related to users";
    public static final String NAME_ENDPOINT = "users";
    public static final String SUMARY_ENDPOINT  ="Save a new user";
    public static final String SUMARY_DSCRIPTION_ENDPOINT  ="Save a new user";


    public static final String USER_NOT_FOUND_BY_EMAIL_WARNING = "❌ User not found with email: {}";
    public static final String USER_NOT_FOUND_BY_EMAIL_EXCEPTION = "User not found with email: ";
    public static final String USER_LOADED_SUCCESSFULLY = "✅ User successfully loaded: {}";
    public static final String USER_ROLE_LOG = " 💼 Role: {}";
    public static final String USER_NOT_FOUND_BY_ID_WARNING = "❌ User not found with ID: {}";
    public static final String USER_NOT_FOUND_BY_ID_EXCEPTION = "User not found with ID: ";
    public static final String USER_LOADED_SUCCESSFULLY_BY_ID = "✅ User successfully loaded by ID: {}";

    public static final String ROLE_PREFIX = "ROLE_";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_NOT_FOUND_DEBUG = "🔒 No JWT token found in Authorization header";
    public static final String ROLES_CLAIM = "roles";
    public static final String ROLES_EXTRACTED_INFO = "🔍 Roles extracted from token: {}";
    public static final String AUTHORITIES_BUILT_INFO = "🛡️ Authorities built from token: {}";
    public static final String AUTHENTICATION_SET_INFO = "✅ Authentication set in SecurityContextHolder for user ID: {} with authorities: {}";
    public static final String INVALID_USER_ID_FORMAT_ERROR = "❌ Invalid user ID format extracted from token subject: {}";

    public static final String EMAIL_CLAIM = "email";
    public static final String NAME_CLAIM = "name";
    public static final String LAST_NAME_CLAIM = "lastName";

}
