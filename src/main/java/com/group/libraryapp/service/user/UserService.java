package com.group.libraryapp.service.user;

// 현재가 유저가 있는지 없는지 등을 확인하고 예외처리를 해준다.

import com.group.libraryapp.dto.user.reponse.UserResponse;
import com.group.libraryapp.dto.user.requset.UserCreateRequest;
import com.group.libraryapp.dto.user.requset.UserUpdateRequest;
import com.group.libraryapp.repository.user.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(JdbcTemplate jdbcTemplate) {
        userRepository = new UserRepository(jdbcTemplate);
    }

    public void saveUser(UserCreateRequest request) {
        userRepository.saveUserInfo(request.getName(), request.getAge());
    }

    public List<UserResponse> getUsers() {
        return userRepository.getUsers();
    }

    public void updateUser(UserUpdateRequest request) {
        if (userRepository.isUserNotExist(request.getId())) {
            throw new IllegalArgumentException();
        }
        userRepository.updateUserName(request.getName(), request.getId());
    }

    public void deleteUser(String name) {
        if (userRepository.isUserNotExist(name)) {
            throw new IllegalArgumentException();
        }
        userRepository.deleteUser(name);
    }
}
