package io.puc.projeto.fidelpoints.auth.config;

import io.puc.projeto.fidelpoints.auth.service.UserAutenticationService;
import io.puc.projeto.fidelpoints.domain.entity.Cliente;
import io.puc.projeto.fidelpoints.domain.entity.Lojista;
import io.puc.projeto.fidelpoints.domain.enums.Role;
import io.puc.projeto.fidelpoints.service.impl.ClienteServiceImpl;
import io.puc.projeto.fidelpoints.service.impl.LojistaServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private LojistaServiceImpl lojistaService;

    private UserAutenticationService userAutenticationService;

    private ClienteServiceImpl clienteService;
    public JwtAuthFilter(JwtService jwtService, LojistaServiceImpl usuarioService, UserAutenticationService userAutenticationService, ClienteServiceImpl clienteService) {
        this.jwtService = jwtService;
        this.lojistaService = usuarioService;
        this.userAutenticationService = userAutenticationService;
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


                String role = jwtService.getClaimByKey(token, "role");

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


            }

        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);


    }
}
