package com.sampana.users.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.sampana.users.dto.UserDto;
import com.sampana.users.entity.User;
import com.sampana.users.repository.AddressRepo;
import com.sampana.users.repository.UserRepository;
import com.sampana.users.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AddressRepo addressRepo;

	@Override
	public List<UserDto> getAllUsers() {
		List<UserDto> userDetails = new ArrayList<>();
		List<User> users = userRepo.findAll();
		if (users == null) {
			return getUsersFromUrl();
		}
		for (User user : users) {
			UserDto userDto = new UserDto();
			userDto.setUser(user);
			userDto.setAddress(addressRepo.findById(user.getAddressId()).get());
			userDetails.add(userDto);
		}

		return getUsersFromUrl();
	}

	public List<UserDto> getUsersFromUrl() {
		RestTemplate restTemplate = new RestTemplate();
		List<UserDto> userDetails = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.setVisibility(
				VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		String fooResourceUrl = "https://jsonplaceholder.typicode.com/users";
		ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
		JSONArray jsonArray = new JSONArray(response.getBody());
		for (Object object : jsonArray) {
			JSONObject ob = (JSONObject) object;
			UserDto addr;
			try {
				addr = mapper.readValue(ob.get("address").toString(), UserDto.class);
				userDetails.add(addr);
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return userDetails;
	}

}
