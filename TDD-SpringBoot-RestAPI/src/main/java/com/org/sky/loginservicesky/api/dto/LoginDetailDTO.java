package com.org.sky.loginservicesky.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDetailDTO {
    private Long customerId;
    private String customerName;
    private String email;
    private String password;


    /* https://www.baeldung.com/java-pojo-javabeans-dto-vo  -- MUST Go*/
}
