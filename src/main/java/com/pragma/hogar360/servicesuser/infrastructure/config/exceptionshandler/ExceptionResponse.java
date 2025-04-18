package com.pragma.hogar360.servicesuser.infrastructure.config.exceptionshandler;

import java.time.LocalDateTime;

public record ExceptionResponse(String message, LocalDateTime timeStamp) {
}