package com.amritsolution.model.db;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.Set;

@Document(collection = "user")
@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppUser {
    @Id
    private String id;
    private String username;
    private String mobileNo;
    private String password;
    private Set<String> roles;
}
