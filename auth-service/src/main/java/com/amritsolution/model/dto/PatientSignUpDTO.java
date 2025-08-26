package com.amritsolution.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatientSignUpDTO extends PatientInfoDTO {
     String password;
}
