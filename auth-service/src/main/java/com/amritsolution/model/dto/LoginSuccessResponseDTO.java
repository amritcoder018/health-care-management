package com.amritsolution.model.dto;

import lombok.Value;

import java.util.List;

@Value
public class LoginSuccessResponseDTO {
    String jwtToken;
    String username;
    List<String> roles;
}
