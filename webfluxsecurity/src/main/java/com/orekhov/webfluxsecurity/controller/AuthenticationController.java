package com.orekhov.webfluxsecurity.controller;

import com.orekhov.webfluxsecurity.dto.AuthenticationRequestDto;
import com.orekhov.webfluxsecurity.dto.AuthenticationResponseDto;
import com.orekhov.webfluxsecurity.dto.UserDto;
import com.orekhov.webfluxsecurity.entity.UserEntity;
import com.orekhov.webfluxsecurity.mapper.UserMapper;
import com.orekhov.webfluxsecurity.security.CustomPrincipal;
import com.orekhov.webfluxsecurity.security.SecurityService;
import com.orekhov.webfluxsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final SecurityService securityService;
    private final UserService userService;
    private final UserMapper userMapper;


    @PostMapping("/register")
    public Mono<UserDto> register(@RequestBody UserDto dto) {
        UserEntity entity = userMapper.mapToUserEntity(dto);
        return userService.registerUser(entity)
                .map(userMapper::mapToUserDto);
    }

    @PostMapping("/login")
    public Mono<AuthenticationResponseDto> login(@RequestBody AuthenticationRequestDto dto) {
        return securityService.authenticate(dto.getUsername(), dto.getPassword())
                .flatMap(tokenDetails -> Mono.just(
                        AuthenticationResponseDto.builder()
                                .userId(tokenDetails.getUserId())
                                .token(tokenDetails.getToken())
                                .issuedAt(tokenDetails.getIssuedAt())
                                .expiresAt(tokenDetails.getExpiresAt())
                                .build()
                ));
    }

    @GetMapping("/info")
    public Mono<UserDto> getUserInfo(Authentication authentication) {
        CustomPrincipal customPrincipal = (CustomPrincipal) authentication.getPrincipal();
        return userService.getUserById(customPrincipal.getId())
                .map(userMapper::mapToUserDto);
    }
}
