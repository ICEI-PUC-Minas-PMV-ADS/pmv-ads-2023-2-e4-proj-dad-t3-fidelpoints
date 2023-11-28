package io.puc.projeto.fidelpoints.jwt;

import io.puc.projeto.fidelpoints.service.impl.ClienteServiceImpl;
import io.puc.projeto.fidelpoints.service.impl.LojistaServiceImpl;
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

    private ClienteServiceImpl clienteService;
    public JwtAuthFilter(JwtService jwtService, LojistaServiceImpl usuarioService, ClienteServiceImpl clienteService) {
        this.jwtService = jwtService;
        this.lojistaService = usuarioService;
        this.clienteService = clienteService;
    }

    @Override
    protected void doFilterInternal( HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain ) throws ServletException, IOException {


        String authorization = httpServletRequest.getHeader("Authorization");

        if( authorization!= null && authorization.startsWith("Bearer") ){
            String token = authorization.split(" ")[1];
            boolean isValid = jwtService.tokenValido(token);

            //obter role que está na claims

            if(isValid){
                String username = jwtService.obterLoginUsuario(token);
                UserDetails lojista = lojistaService.loadUserByUsername(username);
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
