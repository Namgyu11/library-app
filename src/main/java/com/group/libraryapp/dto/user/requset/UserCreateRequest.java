package com.group.libraryapp.dto.user.requset;

public class UserCreateRequest {

    private String name;
    private Integer age; // int -> null 표현 불가능하기 때문

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
