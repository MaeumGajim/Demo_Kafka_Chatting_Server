package com.info.chattingserver.global.config.security

import com.info.chattingserver.global.config.error.handler.ExceptionHandlerFilter
import com.info.chattingserver.global.config.filter.FilterConfig
import com.info.chattingserver.global.config.jwt.JwtTokenResolver
import com.info.chattingserver.global.config.jwt.TokenProvider
import com.vane.badwordfiltering.BadWordFiltering
import mu.KLogger
import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import java.util.*

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val tokenProvider: TokenProvider,
    private val exceptionHandlerFilter: ExceptionHandlerFilter,
    private val tokenResolver: JwtTokenResolver
) {

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf().disable()
            .formLogin().disable()
            .cors()

            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .authorizeRequests()

            .antMatchers("/**").permitAll()
            .anyRequest().permitAll()
            .and()

            .apply(FilterConfig(tokenProvider, tokenResolver, exceptionHandlerFilter))
            .and().build()
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}