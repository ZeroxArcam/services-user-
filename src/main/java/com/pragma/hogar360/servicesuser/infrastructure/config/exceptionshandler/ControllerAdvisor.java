package com.pragma.hogar360.servicesuser.infrastructure.config.exceptionshandler;

import com.pragma.hogar360.servicesuser.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * Global exception handler for REST controllers.
 */
@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleRoleNotFound(RoleNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(ExceptionConstants.ROLE_NOT_FOUND,
                LocalDateTime.now()));
    }
    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidEmail(InvalidEmailException ex){
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.INVALID_EMAIL,
                LocalDateTime.now()));
    }
    @ExceptionHandler(InvalidPhoneNumberException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidPhoneNumber(InvalidPhoneNumberException ex){
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.INVALID_PHONE_NUMBER,
                LocalDateTime.now()));
    }
    @ExceptionHandler(InvalidIdentificationException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidIdentification(InvalidIdentificationException ex){
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.INVALID_ID_NUMBER,
                LocalDateTime.now()));
    }
    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidParameter(InvalidParameterException ex){
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.INVALID_DATE_OF_BIRTH,
                LocalDateTime.now()));
    }
    @ExceptionHandler(InvalidAgeException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidAge(InvalidAgeException ex){
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.INVALID_AGE,
                LocalDateTime.now()));
    }
    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handleEmailAlreadyExist(EmailAlreadyExistException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(ExceptionConstants.EMAIL_ALREADY_EXISTS,
                LocalDateTime.now()));
    }
    @ExceptionHandler(IdNumberAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handleIdNumberAlreadyExist(IdNumberAlreadyExistException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(ExceptionConstants.ID_NUMBER_ALREADY_EXISTS,
                LocalDateTime.now()));
    }
    @ExceptionHandler(PhoneAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handlePhoneAlreadyExist(PhoneAlreadyExistException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(ExceptionConstants.PHONE_NUMBER_ALREADY_EXISTS,
                LocalDateTime.now()));
    }

    @ExceptionHandler(RoleAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handleRoleAlreadyExistsException(RoleAlreadyExistException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(ExceptionConstants.ROLE_ALREADY_EXISTS,
                LocalDateTime.now()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        ExceptionResponse response = new ExceptionResponse(ExceptionConstants.INVALID_DATE_FORMAT, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NameMaxSizeExceededException.class)
    public ResponseEntity<ExceptionResponse> handleNameMaxSizeExceededException(NameMaxSizeExceededException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.FIELD_NAME_MAX_LENGTH_MESSAGE,
                LocalDateTime.now()));
    }
    @ExceptionHandler(EmptyNameException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyNameException(EmptyNameException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.FIELD_NAME_EMPTY_MESSAGE,
                LocalDateTime.now()));
    }

    @ExceptionHandler(LastNameMaxSizeExceededException.class)
    public ResponseEntity<ExceptionResponse> handleLastNameMaxSizeExceededException(LastNameMaxSizeExceededException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.FIELD_LAST_NAME_MAX_LENGTH_MESSAGE,
                LocalDateTime.now()));
    }
    @ExceptionHandler(EmptyLastNameException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyLastNameException(EmptyLastNameException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.FIELD_LAST_NAME_EMPTY_MESSAGE,
                LocalDateTime.now()));
    }

    @ExceptionHandler(EmptyDescriptionException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyDescriptionException(EmptyDescriptionException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.FIELD_DESCRIPTION_EMPTY_MESSAGE,
                LocalDateTime.now()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUsernameNotFound(UsernameNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(ex.getMessage(), // Usamos el mensaje original de la excepción
                LocalDateTime.now()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionResponse> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponse(ex.getMessage(), LocalDateTime.now()));
    }
}
