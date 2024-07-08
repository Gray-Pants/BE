package com.poku.graypants.domain.user.web;

import static com.poku.graypants.global.util.ApiResponseUtil.error;
import static com.poku.graypants.global.util.ApiResponseUtil.success;

import com.poku.graypants.domain.user.application.UserService;
import com.poku.graypants.global.config.oauth.PrincipalDetails;
import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ApiResult<User>> me(Authentication authentication){
        return new ResponseEntity<>(success((User)authentication.getPrincipal()), HttpStatus.OK);
    }


}
