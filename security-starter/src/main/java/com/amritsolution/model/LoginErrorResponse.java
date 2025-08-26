package com.amritsolution.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LoginErrorResponse {
    int status;
    String error;
    String message;
    String path;
}
