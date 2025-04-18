package com.pragma.hogar360.servicesuser.application.utils.constants;

public final class ApplicationConstants {
    private ApplicationConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String SAVE_USER_RESPONSE_MESSAGE = "User created successfully.";

    public static final String AUTH_ATTEMPT_LOGIN_MESSAGE = "🔐 Attempting authentication for user with email: {}";
    public static final String AUTH_SUCCESSFUL_LOGIN_MESSAGE = "✅ User authenticated successfully!";
    public static final String AUTH_USER_INFO_MESSAGE = "👤 User Info:";
    public static final String AUTH_USER_ID_LOG = "   🆔 ID: {}";
    public static final String AUTH_USER_NAME_LOG = "   📛 Name: {}";
    public static final String AUTH_USER_EMAIL_LOG = "   📧 Email: {}";
    public static final String AUTH_USER_ROLE_LOG = "   💼 Role: {}";
    public static final String AUTH_USER_DETAILS_NOT_FOUND_ERROR = "⚠️ User authenticated but details could not be found in the system.";
    public static final String AUTH_INVALID_CREDENTIALS_WARNING = "❌ Authentication failed: invalid credentials for email {}";
    public static final String AUTH_USER_DETAILS_NOT_FOUND_EXCEPTION_MESSAGE = "User details not found after successful authentication.";
    public static final String AUTH_INVALID_CREDENTIALS_MESSAGE = "Invalid credentials";
}