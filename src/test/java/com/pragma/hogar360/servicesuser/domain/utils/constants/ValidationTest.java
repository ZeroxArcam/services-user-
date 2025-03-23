package com.pragma.hogar360.servicesuser.domain.utils.constants;

import static org.junit.jupiter.api.Assertions.*;
import com.pragma.hogar360.servicesuser.domain.exceptions.*;
import org.junit.jupiter.api.Test;

class ValidationTest {

    @Test
    void isValidEmail_shouldReturnTrueForValidEmail() {
        assertTrue(Validation.isValidEmail("test@example.com"));
    }

    @Test
    void isValidEmail_shouldReturnFalseForInvalidEmail() {
        assertFalse(Validation.isValidEmail("invalid-email"));
    }

    @Test
    void isValidEmail_shouldReturnFalseForNullEmail() {
        assertFalse(Validation.isValidEmail(null));
    }

    @Test
    void isValidEmail_shouldReturnFalseForBlankEmail() {
        assertFalse(Validation.isValidEmail(" "));
    }

    @Test
    void isValidPhone_shouldReturnTrueForValidPhone() {
        assertTrue(Validation.isValidPhone("1234567890"));
    }

    @Test
    void isValidPhone_shouldReturnTrueForValidPhoneWithCountryCode() {
        assertTrue(Validation.isValidPhone("+11234567890"));
    }

    @Test
    void isValidPhone_shouldReturnFalseForInvalidPhone() {
        assertFalse(Validation.isValidPhone("invalid-phone"));
    }

    @Test
    void isValidPhone_shouldReturnFalseForNullPhone() {
        assertFalse(Validation.isValidPhone(null));
    }

    @Test
    void isValidPhone_shouldReturnFalseForBlankPhone() {
        assertFalse(Validation.isValidPhone(" "));
    }

    @Test
    void validateName_shouldNotThrowExceptionForValidName() {
        assertDoesNotThrow(() -> Validation.validateName("Valid Name"));
    }

    @Test
    void validateName_shouldThrowExceptionForNullName() {
        assertThrows(NullPointerException.class, () -> Validation.validateName(null));
    }

    @Test
    void validateName_shouldThrowExceptionForBlankName() {
        assertThrows(EmptyNameException.class, () -> Validation.validateName(" "));
    }

    @Test
    void validateName_shouldThrowExceptionForLongName() {
        assertThrows(NameMaxSizeExceededException.class, () -> Validation.validateName("This is a very long name that exceeds the maximum length This is a very long name that exceeds the maximum length This is a very long name that exceeds the maximum length"));
    }

    @Test
    void validateLastName_shouldNotThrowExceptionForValidLastName() {
        assertDoesNotThrow(() -> Validation.validateLastName("Valid Last Name"));
    }

    @Test
    void validateLastName_shouldThrowExceptionForNullLastName() {
        assertThrows(NullPointerException.class, () -> Validation.validateLastName(null));
    }

    @Test
    void validateLastName_shouldThrowExceptionForBlankLastName() {
        assertThrows(EmptyLastNameException.class, () -> Validation.validateLastName(" "));
    }

    @Test
    void validateLastName_shouldThrowExceptionForLongLastName() {
        assertThrows(LastNameMaxSizeExceededException.class, () -> Validation.validateLastName("This is a very long name that exceeds the maximum length This is a very long name that exceeds the maximum length This is a very long last name that exceeds the maximum length"));
    }

    @Test
    void validateDescription_shouldNotThrowExceptionForValidDescription() {
        assertDoesNotThrow(() -> Validation.validateDescription("Valid Description"));
    }

    @Test
    void validateDescription_shouldThrowExceptionForNullDescription() {
        assertThrows(NullPointerException.class, () -> Validation.validateDescription(null));
    }

    @Test
    void validateDescription_shouldThrowExceptionForBlankDescription() {
        assertThrows(EmptyDescriptionException.class, () -> Validation.validateDescription(" "));
    }

    @Test
    void validateDescription_shouldThrowExceptionForLongDescription() {
        assertThrows(DescriptionMaxSizeExceededException.class, () -> Validation.validateDescription("This is a very long description that exceeds the maximum length This is a very long description that exceeds the maximum length  This is a very long description that exceeds the maximum length  This is a very long description that exceeds the maximum length  This is a very long description that exceeds the maximum length  "));
    }
}