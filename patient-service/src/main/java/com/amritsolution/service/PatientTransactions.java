package com.amritsolution.service;

import org.springframework.http.ResponseEntity;

public interface PatientTransactions {
    ResponseEntity<?> makePayment();
    ResponseEntity<?> getRefund();
    ResponseEntity<?> checkPaymentStatus();
}
