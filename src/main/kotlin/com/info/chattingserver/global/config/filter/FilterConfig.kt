package com.info.chattingserver.global.config.filter

import com.info.chattingserver.global.config.error.handler.ExceptionHandlerFilter
import com.info.chattingserver.global.config.jwt.JwtTokenResolver
import com.info.chattingserver.global.config.jwt.TokenProvider
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class FilterConfig(
    private val tokenProvider: TokenProvider,
    private val tokenResolver: JwtTokenResolver,
    private val exceptionHandlerFilter: ExceptionHandlerFilter
): SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity.addFilterBefore(TokenFilter(tokenResolver, tokenProvider), UsernamePasswordAuthenticationFilter::class.java)
        httpSecurity.addFilterBefore(exceptionHandlerFilter, TokenFilter::class.java)
    }
}