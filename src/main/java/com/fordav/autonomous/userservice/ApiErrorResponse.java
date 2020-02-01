package com.fordav.autonomous.userservice;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class ApiErrorResponse {

    private String context;
    private String errorMessage;
    private OffsetDateTime timestamp;
}
