package com.example.d3nserver.user.controller;

import com.example.d3nserver.common.base.BaseResponse;
import com.example.d3nserver.user.dto.UserDataFormDto;
import com.example.d3nserver.user.domain.User;
import com.example.d3nserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/user")
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("/userForm/save")
    public BaseResponse<User> userFormSave(@RequestBody UserDataFormDto inputForm, @RequestParam("id") String id){
        User findUser = userService.findById(id).get();
        findUser.setNickname(inputForm.getNickname());
        findUser.setGender(inputForm.getGender());
        findUser.setBirthYear(inputForm.getBirthYear());
        findUser.setCategoryList(inputForm.getCategoryList());
        userService.save(findUser);
        return BaseResponse.ofSuccess(findUser);
    }

}
