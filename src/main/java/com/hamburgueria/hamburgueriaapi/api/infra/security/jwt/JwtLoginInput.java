package com.hamburgueria.hamburgueriaapi.api.infra.security.jwt;

import lombok.Data;

@Data
class JwtLoginInput {
    private String username;
    private String password;
}