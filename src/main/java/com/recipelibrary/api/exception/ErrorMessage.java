package com.recipelibrary.api.exception;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ErrorMessage {
    private final Instant timestamp = Instant.now();
    private String message;
    private int code;
}
