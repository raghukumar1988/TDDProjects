package com.org.sky.loginservicesky.api.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor //Step 4
@AllArgsConstructor //Step 4
@Builder // Step 4 - To make object creation easier
public class LoginDetail {

    private Long customerId;
    private String customerName;
    private String email;
    private String password;
}
