package com.digital.mobile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.digital.mobile.api.UserService;
import com.digital.mobile.dao.UserDao;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
    UserDao userdao;


	public List query() {
		List list=userdao.query();
		
		return list;
	}
	public List queryLogin(String id, String name) {
		List list=userdao.queryLogin(id, name);

		return list;
	}
	@Override
	public int insert(String id, String name) {
		int num=userdao.register(id,name);
		return num;
	}
	
}