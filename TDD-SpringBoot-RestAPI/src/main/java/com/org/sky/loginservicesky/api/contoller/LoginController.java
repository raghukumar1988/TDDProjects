package com.org.sky.loginservicesky.api.contoller;

import com.org.sky.loginservicesky.api.dto.LoginDetailDTO;
import com.org.sky.loginservicesky.api.model.LoginDetail;
import com.org.sky.loginservicesky.api.service.LoginDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/login") // TDD Step 1 - add this mapping to fix http 404 error
public class LoginController {

    // Step 5  -- Injecting Service Bean
    private LoginDetailService loginDetailService;
    private ModelMapper modelMapper;

    //@Autowired
    /* As of Spring 4.3, classes with a single constructor can omit the @Autowired annotation. */
    public LoginController(LoginDetailService loginDetailService, ModelMapper mapper) {
        this.loginDetailService = loginDetailService;
        this.modelMapper= mapper;
    }

    @PostMapping("/create") // TDD Step 1 - add this mapping to fix http 404 error
    @ResponseStatus(HttpStatus.CREATED)  //TDD  Step 2 - add this line to match expected http 201
    //public LoginDetail createLoginDetails(){  // before Step 4
    public LoginDetailDTO createLoginDetails(@RequestBody LoginDetailDTO loginDetailDTO){ // Processing Request body

        // remove below commented part once started to use request body; rerun test and should pass now.
        /* LoginDetailDTO loginDetailDTO= new LoginDetailDTO();
        loginDetailDTO.setCustomerId(222L);
        loginDetailDTO.setCustomerName("Raghu");
        loginDetailDTO.setEmail("rk@gmail.com");
        loginDetailDTO.setPassword("password"); */

        // return null; // TDD - During step 2
        //return loginDetailDTO;  // TDD step 3 - set values and return this

        //Step 5 -- make call to service layer
        /*LoginDetail loginDetail = LoginDetail.builder()
                .customerName(loginDetailDTO.getCustomerName())
                .customerId(loginDetailDTO.getCustomerId())
                .email(loginDetailDTO.getEmail())
                .password(loginDetailDTO.getPassword())
                .build();

        LoginDetail result = loginDetailService.save(loginDetail);*/ // commented after ModelMapper inclusion
        LoginDetail result = loginDetailService.save(modelMapper.map(loginDetailDTO,LoginDetail.class)); // Step 6


        /*LoginDetailDTO resultDTO = LoginDetailDTO.builder() // Step 5 -- mapping service result to DTO
                .customerName(result.getCustomerName())
                .customerId(result.getCustomerId())
                .email(result.getEmail())
                .password(result.getPassword())
                .build();
        return resultDTO;*/ // commented after ModelMapper inclusion

        return modelMapper.map(result,LoginDetailDTO.class);
    }



    // Step 6 - Including Model Mapper
}
