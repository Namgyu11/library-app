package com.group.libraryapp.repository.user;


import com.group.libraryapp.dto.user.reponse.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

// SQL을 사용해 실제 DB와의 통신을 담당한다.
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserResponse> getUsers() {
        String sql = "select * from user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
        });
    }

    public void saveUserInfo(String name, Integer age) {
        String sql = "insert into user(name, age) values(?,?)";
        jdbcTemplate.update(sql, name, age);
    }

    //주어진 유저 아이디를 받 아서 jdbc를 이용해서 유저의 존재유무를 확인
    public boolean isUserNotExist(long id) {
        String readsql = "select * from user where id= ?";
        return jdbcTemplate.query(readsql, (rs, rowNum) -> 0, id).isEmpty();
    }

    // DB와 접근해서 SQL를 날리는 역할
    public void updateUserName(String name, long id) {
        String sql = "update user set name = ? where id = ? ";
        jdbcTemplate.update(sql, name, id);
    }

    //오버로드
    public boolean isUserNotExist(String name) {
        String readsql = "select * from user where name= ?";
        return jdbcTemplate.query(readsql, (rs, rowNum) -> 0, name).isEmpty();
    }

    public void deleteUser(String name) {
        String sql = "delete from user where name = ?";
        jdbcTemplate.update(sql, name); // 객체가 하나이기 때문에 생성자 따로 만들 필요 X

    }
}
