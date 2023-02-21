package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.reponse.UserResponse;
import com.group.libraryapp.dto.user.requset.UserCreateRequest;
import com.group.libraryapp.dto.user.requset.UserUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {

    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // save 메소드에 객체를 넣어주면 insert sql이 자동으로 날라간다.
    @Transactional // 아래에 있는 함수가 시작될 때 start transaction;을 해준다(트랜잭션을 시작!)
                  // 함수가 예외 없이 잘 끝났다면 commit
                  // 혹시라도 문제가 있다면 rollback
    public void saveUser(UserCreateRequest request){
      userRepository.save(new User(request.getName(),request.getAge()));
    }

    //유저 조회 기능
    @Transactional(readOnly = true)
    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList()); // 스트림 활용

    }

    //유저 업데이트 기능
    @Transactional
    public void  updateUser(UserUpdateRequest request){

        //select * from user where id = ?
        // Optional<User>
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        //User의 객체를 가져와서 업데이트

        user.updateName(request.getName());
        //userRepository.save(user);
    }
    @Transactional
    public  void deleteUser(String name){
        // select * from user where name = ?
//        User user = userRepository.findByName(name);
//        if(user == null){
//            throw new IllegalArgumentException();
//        }
        User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
        userRepository.delete(user);
    }

}
