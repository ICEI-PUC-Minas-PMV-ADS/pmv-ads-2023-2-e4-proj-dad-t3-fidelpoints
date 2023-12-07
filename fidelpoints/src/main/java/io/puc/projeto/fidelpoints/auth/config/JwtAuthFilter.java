package io.puc.projeto.fidelpoints.auth.config;

import io.puc.projeto.fidelpoints.auth.service.UserAutenticationService;
import io.puc.projeto.fidelpoints.entity.Cliente;
import io.puc.projeto.fidelpoints.entity.Lojista;
import io.puc.projeto.fidelpoints.enums.Role;
import io.puc.projeto.fidelpoints.exception.erros.ClienteNotFoundException;
import io.puc.projeto.fidelpoints.exception.erros.LojistaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    private UserAutenticationService userAutenticationService;
    public JwtAuthFilter(UserAutenticationService userAutenticationService) {
        this.userAutenticationService = userAutenticationService;
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
                String username = jwtService.getUsernameByToken(token);

                String role = jwtService.getClaimByKey(token, "role");

                try {
                    UsernamePasswordAuthenticationToken user;

                    if(role.equalsIgnoreCase(Role.ROLE_CLIENTE.name())){
                        Cliente cliente = userAutenticationService.loadCliente(username);

                        user = new
                                UsernamePasswordAuthenticationToken(cliente, cliente.getSenha(),
                                cliente.getAuthorities());
                    } else{
                        Lojista lojista = userAutenticationService.loadLojista(username);

                        user = new
                                UsernamePasswordAuthenticationToken(lojista, lojista.getSenha(),
                                lojista.getAuthorities());
                    }

                    user.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(user);
                } catch (ClienteNotFoundException | LojistaNotFoundException e) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
                }


            }

        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
