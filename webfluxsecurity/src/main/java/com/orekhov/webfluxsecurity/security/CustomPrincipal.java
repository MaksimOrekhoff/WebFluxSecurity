package com.orekhov.webfluxsecurity.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.Principal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomPrincipal implements Principal {
    private Long id;
    private String name;
}
