package io.puc.projeto.fidelpoints.auth.config;

import io.puc.projeto.fidelpoints.auth.service.impl.UserAutenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
//Habilita o @PreAuthorize, caso nao estiver com essa configuração precisará adicionar a configuracao ao SecurityFilterChain
public class SecurityConfig {

    @Autowired
    private UserAutenticationServiceImpl userAutenticationServiceImpl;

    @Autowired
    private JwtService jwtService;

    @Bean
    public OncePerRequestFilter jwtfilter() {
        return new JwtAuthFilter(userAutenticationServiceImpl);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic((basic) -> basic.securityContextRepository(new HttpSessionSecurityContextRepository()))
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(HttpMethod.POST, "/api/lojistas", "/api/auth/**", "/api/clientes")
                        .permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/clientes")
                        .hasRole("CLIENTE")

                        .requestMatchers("/h2-console/**", "/h2**")
                        .permitAll()

                       // .requestMatchers("/api/clientes")
                        //s.hasRole("CLIENTE")

                        .requestMatchers("/api/lojistas")
                        .hasRole("LOJISTA")

                        .requestMatchers("/api/pedidos/**")
                        .hasAnyRole("CLIENTE", "LOJISTA")

                        // .requestMatchers("/api/produtos/**")
                        //.hasAnyRole("CLIENTE", "LOJISTA")
                        .anyRequest().denyAll()
                )
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtfilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring()
                .requestMatchers(
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/h2/**",
                        "/h2-console",
                        "/swagger-ui.html",
                        "/webjars/**");
    }


}