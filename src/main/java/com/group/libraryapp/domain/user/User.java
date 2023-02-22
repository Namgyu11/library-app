package com.group.libraryapp.domain.user;

import javax.persistence.*;

@Entity //저장되고 관리되어야하는 것

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 설정
    private Long id = null;

    @Column(nullable = false, length = 20, name = "name") // name- varchar(20)
    // null이 들어갈 수 있는지 여부, 길이 제한, DB에서의 column 이름 등등...
    private String name;
    private Integer age;

    protected User() {
        //JPA를 사용하기 위해서는 기본 생성자가 꼭 필요하다.
    }

    public User(String name, Integer age) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어 왔습니다.", name));
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name) {
        this.name = name;
    }
}
