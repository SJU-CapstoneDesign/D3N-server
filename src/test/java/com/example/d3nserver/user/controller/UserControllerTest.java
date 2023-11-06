package com.example.d3nserver.user.controller;

import com.example.d3nserver.news.domain.Field;
import com.example.d3nserver.user.domain.Gender;
import com.example.d3nserver.user.domain.User;
import com.example.d3nserver.user.repository.UserRepository;
import com.example.d3nserver.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@Commit
class UserControllerTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    void userFormSave() {
        //유저 저장
        String testId = "abc";
        User user = new User();
        user.setId(testId);
        user.setGender(Gender.MAN);
        user.setBirthDay(1999L);
        user.setNickname(null);
        List<Field> fields = new ArrayList<>();
        fields.add(Field.CULTURE);
        fields.add(Field.LIFE);
        fields.add(Field.IT);
        user.setCategoryList(fields);
        User savedUser = userService.save(user);
        User findUser = userService.findById(user.getId()).get();
        org.assertj.core.api.Assertions.assertThat(savedUser).isEqualTo(findUser);
    }

    @Test
    void userSave(){
        String testId = "def";
        User user = new User();
        user.setId(testId);
        User savedUser = userService.save(user);
    }

}