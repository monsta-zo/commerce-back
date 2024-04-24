package org.example.commerceback.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.commerceback._core.utils.ApiUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRequest.RegisterDTO requestDTO, @RequestParam("type") String type,Errors errors) {
        return ResponseEntity.ok().body(ApiUtils.success("회원가입이 완료되었습니다.", userService.register(requestDTO, type)));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO requestDTO) {
        return ResponseEntity.ok().body(ApiUtils.success("로그인이 완료되었습니다.", userService.login(requestDTO)));
    }

    @GetMapping("/hello")
    public String hello() {
        return "hi";
    }
}
