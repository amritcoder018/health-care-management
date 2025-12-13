package com.amritsolution.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ServiceRunningStatus {
    private int statusCode;
    private final String serviceName;

}
