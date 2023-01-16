package com.sandbox.sentinel.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class SecurityConfigurer {

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}