package com.org.sky.loginservicesky.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.sky.loginservicesky.api.dto.LoginDetailDTO;
import com.org.sky.loginservicesky.api.model.LoginDetail;
import com.org.sky.loginservicesky.api.service.LoginDetailService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test") /// Read here --  https://www.baeldung.com/spring-profiles
@WebMvcTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    /*
    What is @MockBean?
    Annotation that can be used to add mocks to a Spring ApplicationContext.
    If any existing single bean of the same type defined in the context will be replaced by the mock,
     if no existing bean is defined a new one will be added.

    When to use?
    If your test needs to rely on the Spring Boot container and you want to add or mock one of the container beans.
     Example: Service classes
     */

    @Autowired
    MockMvc mockMvc;

    @MockBean
    LoginDetailService service;

    String URI = "/api/login/create";

    @Test
    @DisplayName("YOu should successfully create/save user Login Details")
    void createLoginDetailsTest() throws Exception {
        LoginDetailDTO loginDetailDto = LoginDetailDTO.builder() // know the syntax to call builder
                .customerName("RaghuRaja")  // Building DTO
                .customerId(222L)
                .email("rk@gmail.com")
                .password("password")
                .build();
        // Step 5 -- use Mockito or BDDMockito
        LoginDetail loginDetail = LoginDetail.builder().customerName("RaghuRaja") // Building Model
                .customerId(222L)
                .email("rk@gmail.com")
                .password("password")
                .build();

        Mockito.when(service.save(Mockito.any(LoginDetail.class))).thenReturn(loginDetail);
        //String requestJson = new ObjectMapper().writeValueAsString(null);  // was set to null until Step 3
        String requestJson = new ObjectMapper().writeValueAsString(loginDetailDto);  //
        MockHttpServletRequestBuilder content = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson); // Ctrl+Alt+V to create variable with correct Type
        mockMvc
                .perform(content)
                .andExpect(MockMvcResultMatchers.status().isCreated())// we can use static import for readability
                .andExpect(MockMvcResultMatchers.jsonPath("customerId").value("222"))
                // below line fails as you have hardcoded response in controller, to fix this process request body
                .andExpect(MockMvcResultMatchers.jsonPath("customerName").value("RaghuRaja")) // Step 4 - expect value similer to Request body
                .andExpect(MockMvcResultMatchers.jsonPath("email").value("rk@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("password").value("password"));
    }

    @Test
    @DisplayName("Should throw validation error when not enough data to create user login details")
    void invalidCreateLoginDetailsTest() {

    }
}
