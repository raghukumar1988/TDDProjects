package com.org.sky.loginservicesky.api.service;

import com.org.sky.loginservicesky.api.dao.LoginDetailDao;
import com.org.sky.loginservicesky.api.model.LoginDetail;
import com.org.sky.loginservicesky.api.service.impl.LoginDetailServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// Stage 2 Step 1
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class LoginDetailServiceTest {
    //@InjectMocks  -- Not needed because this is only needed when we have Mocks which needs to be injected
    /*  @Mock creates a mock, and @InjectMocks creates an instance of the class
    and injects the mocks that are created with the @Mock annotations into this instance. */
    LoginDetailService loginDetailService;

    @MockBean
    LoginDetailDao loginDetailDao;

    @BeforeEach
        // Stage 2 Step 2
    void setup() {
        loginDetailService = new LoginDetailServiceImpl(loginDetailDao);
    }

    @Test
    @DisplayName("Should be able to save Login Details")
    void saveLoginDetailsTest() {
        LoginDetail loginDetail = LoginDetail.builder().customerName("RaghuRaja") // Building Model
                .customerId(222L)// Stage 2 Step 1
                .email("rk@gmail.com")
                .password("password")
                .build();
        Mockito.when(loginDetailService.save(Mockito.any(LoginDetail.class))).thenReturn(LoginDetail.builder()
                .customerName("RaghuRajaFromMOck") // after adding this test passes for first time
                .customerId(222L)// Stage 2 Step 2
                .email("rk@gmail.com")
                .password("password")
                .build());
        LoginDetail save = loginDetailService.save(loginDetail);
        Assertions.assertEquals("RaghuRajaFromMOck", save.getCustomerName());
        Assertions.assertEquals(222L, save.getCustomerId());
        Assertions.assertEquals("rk@gmail.com", save.getEmail());
        Assertions.assertEquals("password", save.getPassword());
    }
}
