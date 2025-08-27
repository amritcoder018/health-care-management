package com.amritsolution.service;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPost;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public interface MicroserviceConnector {
    ResponseEntity<?> establishConnection(HttpMethod httpMethod, Object object, HttpHeaders httpHeaders);
}
