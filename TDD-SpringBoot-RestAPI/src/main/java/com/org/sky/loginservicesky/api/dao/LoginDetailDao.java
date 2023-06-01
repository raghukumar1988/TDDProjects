package com.org.sky.loginservicesky.api.dao;

import com.org.sky.loginservicesky.api.model.LoginDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginDetailDao extends JpaRepository<LoginDetail,Long> {
}
