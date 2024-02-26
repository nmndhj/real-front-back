package com.example.demo.auth.controller;

import com.example.demo.auth.entity.User;
import com.example.demo.auth.repository.MemberRepository;
import com.example.demo.auth.service.JwtService;
import com.example.demo.common.error.BizException;
import com.example.demo.common.error.ErrorCode;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TokenController {

    @Autowired
    JwtService jwtService;

    private final MemberRepository memberRepository;

    @PostMapping("/account/login")
    public ResponseEntity login(@RequestBody Map<String, String> params,
                                HttpServletResponse res) {
        List<User> users = memberRepository.findByEmailAndPwd(params.get("email"), params.get("password"));

        if (!users.isEmpty()) {
            int id = users.get(0).getId();
            String token = jwtService.getToken("id", id);

            Cookie cookie = new Cookie("weeklyNote", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            res.addCookie(cookie);

            return new ResponseEntity<>(id, HttpStatus.OK);
        }

        throw new BizException(ErrorCode.TARGET_DATA_NOT_FOUND);
    }

    @GetMapping("/account/check")
    public ResponseEntity check(@CookieValue(value = "weeklyNote", required = false) String token) {

        Claims claims = jwtService.getClaims(token);

        if (claims != null) {
            int id = Integer.parseInt(claims.get("id").toString());
            return new ResponseEntity<>(id, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/account/logout")
    public ResponseEntity logout(HttpServletResponse res) {
        Cookie cookie = new Cookie("weeklyNote", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<Object> addBookmark(@RequestBody User user){
        List<User> users = memberRepository.findByEmailAndPwd(user.getUserId(), user.getUserId());

        if(users.isEmpty()){
            memberRepository.save(user);
        }else{
            throw new BizException(ErrorCode.DUPLICATE_RESOURCE);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getUserInfo(@CookieValue(value = "weeklyNote", required = false) String token){
        Claims claims = jwtService.getClaims(token);

        if (claims != null) {
            int id = Integer.parseInt(claims.get("id").toString());
            User user = memberRepository.findById(id).orElseThrow(null);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
