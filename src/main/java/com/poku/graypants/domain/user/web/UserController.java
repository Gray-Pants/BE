package com.poku.graypants.domain.user.web;

import static com.poku.graypants.global.util.ApiResponseUtil.success;

import com.poku.graypants.global.config.oauth.PrincipalDetails;
import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<ApiResult<User>> me(Authentication authentication){

        System.out.println(authentication.getDetails());
        return new ResponseEntity<>(success((User)authentication.getPrincipal()), HttpStatus.OK);
    }
}
