package io.projeto.backend.fidelpoints.jwt;

import io.stephanie.backend.fidelpoints.service.impl.LojistaServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private LojistaServiceImpl lojistaService;

    public JwtAuthFilter(JwtService jwtService, LojistaServiceImpl usuarioService) {
        this.jwtService = jwtService;
        this.lojistaService = usuarioService;
    }

    @Override
    protected void doFilterInternal( HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain ) throws ServletException, IOException {

        String authorization = httpServletRequest.getHeader("Authorization");

        if( authorization!= null && authorization.startsWith("Bearer") ){
            String token = authorization.split(" ")[1];
            boolean isValid = jwtService.tokenValido(token);

            if(isValid){
                String loginLojista = jwtService.obterLoginUsuario(token);
                UserDetails lojista = lojistaService.loadUserByUsername(loginLojista);
                UsernamePasswordAuthenticationToken user = new
                        UsernamePasswordAuthenticationToken(lojista, null,
                        lojista.getAuthorities());
                user.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(user);


            }

        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);


    }
}
