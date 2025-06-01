package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	public CustomUserDetailsService customUserDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())

            
        .authorizeHttpRequests(auth -> auth
        		.requestMatchers("/", "/login", "/signup", "/reservation/list", "/css/**", "/js/**", "/imgs/**",
                        "/sub_1.html", "/sub_1_1.html", "/sub_2.html", "/sub_3.html").permitAll()
        		.anyRequest().permitAll()

        	)

            // 로그인 설정
	        .formLogin(form -> form
	            .loginPage("/login") 
	            .loginProcessingUrl("/login")
	            .defaultSuccessUrl("/", true)
	            .failureUrl("/login?error=true")
	            .permitAll()
	        )


            // 로그아웃 설정
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }
}
