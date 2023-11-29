package io.puc.projeto.fidelpoints.config;

import io.puc.projeto.fidelpoints.jwt.JwtAuthFilter;
import io.puc.projeto.fidelpoints.jwt.JwtService;
import io.puc.projeto.fidelpoints.service.impl.ClienteServiceImpl;
import io.puc.projeto.fidelpoints.service.impl.LojistaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private LojistaServiceImpl lojistaService;

    private ClienteServiceImpl clienteService;

    @Autowired
    private io.puc.projeto.fidelpoints.service.impl.UserAutenticationServiceImpl userAutenticationServiceImpl;

    @Autowired
    private JwtService jwtService;

    @Bean
    public OncePerRequestFilter jwtfilter() {
        return new JwtAuthFilter(jwtService, lojistaService, userAutenticationServiceImpl, clienteService);
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userAutenticationServiceImpl)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic((basic) -> basic.securityContextRepository(new HttpSessionSecurityContextRepository()))
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(HttpMethod.POST, "/api/lojistas", "/api/auth/**", "/api/clientes")
                        .permitAll()

                        .requestMatchers("/api/clientes/teste")
                        .hasAnyAuthority("CLIENTE")

                        .requestMatchers("/api/lojistas/teste")
                        .hasAnyAuthority("LOJISTA")

                        .requestMatchers("/api/pedidos/**")
                        .hasAnyAuthority("CLIENTE", "LOJISTA")

                       // .requestMatchers("/api/produtos/**")
                        //.hasAnyRole("CLIENTE", "LOJISTA")

                        .anyRequest().denyAll()
                )
                // .and()
                // .sessionManagement()
                // .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //  .and()

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
