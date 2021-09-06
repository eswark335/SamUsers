package com.sampana.users.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sampana.users.dto.UserDto;

@Service
public interface UserService {

	List<UserDto> getAllUsers();

}
