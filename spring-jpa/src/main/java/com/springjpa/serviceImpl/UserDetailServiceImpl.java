package com.springjpa.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjpa.dao.UserRepository;
import com.springjpa.model.UserDetail;
import com.springjpa.service.UserDetailService;

@Service
public class UserDetailServiceImpl implements UserDetailService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetail addUser(UserDetail ud) {
		// TODO Auto-generated method stub
		return userRepository.save(ud);
		//return null;
	}

	@Override
	public List<UserDetail> fetchUser(UserDetail userDetail) {
		// TODO Auto-generated method stub
		return userRepository.fetchUser(userDetail.getUserName(), userDetail.getUserAdd());
	}

}
