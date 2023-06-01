package com.org.sky.loginservicesky.api.service.impl;

import com.org.sky.loginservicesky.api.dao.LoginDetailDao;
import com.org.sky.loginservicesky.api.model.LoginDetail;
import com.org.sky.loginservicesky.api.service.LoginDetailService;

public class LoginDetailServiceImpl implements LoginDetailService {

    private LoginDetailDao repository;

    public LoginDetailServiceImpl(LoginDetailDao loginDetailDao){
        this.repository= loginDetailDao;
    }

    @Override
    public LoginDetail save(LoginDetail loginDetail) {
        return repository.save(loginDetail);
    }
}
