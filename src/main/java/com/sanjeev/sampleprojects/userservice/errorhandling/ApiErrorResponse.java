package com.sanjeev.sampleprojects.userservice.errorhandling;

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
