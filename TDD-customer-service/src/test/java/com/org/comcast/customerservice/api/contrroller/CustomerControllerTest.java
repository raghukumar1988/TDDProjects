package com.org.comcast.customerservice.api.contrroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.comcast.customerservice.api.dto.CustomerDTO;
import com.org.comcast.customerservice.api.model.Customer;
import com.org.comcast.customerservice.api.service.CustomerService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

    /*
    What is @MockBean?
    Annotation  used to add mocks to a Spring ApplicationContext.
    If any existing single bean of the same type defined in the context will be replaced by the mock,
     if no existing bean is defined a new one will be added.

    When to use?
    If your test needs to rely on the Spring Boot container and you want to add or mock one of the container beans.
     Example: Service classes
     */

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    String URI = "/customers/createCustomer";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customerService;  // step 1 of this stage


    @Test
    @DisplayName("Should successfully create a new customer from Request Body")
    void createConsumerFromRequestBody() throws Exception {
        CustomerDTO customerDTO = CustomerDTO.builder().customerId(979054L).customerName("RaghuWithBody").activationStatus(true).gender("Male").build();
        String content = new ObjectMapper().writeValueAsString(customerDTO);
        Customer savedCustomer = Customer.builder().customerId(979054L).customerName("RaghuWithBody").activationStatus(true).gender("Male").build();
        when(customerService.save(any(Customer.class))).thenReturn(savedCustomer); // step 2 of this stage; create classes with help of IDE
        MockHttpServletRequestBuilder requestBuilder = post(URI)// Ctrl+Alt+V to create variable with correct Type
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())// we can use static import for readability
                .andExpect(jsonPath("customerId").value(979054L))
                .andExpect(jsonPath("customerName").value("RaghuWithBody"))
                .andExpect(jsonPath("activationStatus").value(true))
                .andExpect(jsonPath("gender").value("Male"));


    }
}

