package com.org.comcast.customerservice.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    private Long customerId;
    private String customerName;
    private boolean activationStatus;
    private String gender;
}
