package io.projeto.backend.fidelpoints.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.stephanie.backend.fidelpoints.FidelpointsApplication;
import io.stephanie.backend.fidelpoints.domain.entity.Lojista;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken( Lojista lojista ){

        long expString = Long.valueOf(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date .from(instant);


        return Jwts
                .builder()
                .setSubject(lojista.getLogin())
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura )
                .compact();
    }

    public Claims obterClaims (String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenValido( String token ) {
        try {

            Claims claims = obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data = dataExpiracao.toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(data);

        } catch (Exception exception) {
            return false;
        }
    }

   public String obterLoginUsuario (String token) throws ExpiredJwtException{
        return (String) obterClaims(token).getSubject();
    }


    public static void main(String[] args){
        ConfigurableApplicationContext contexto = SpringApplication.run(FidelpointsApplication.class);
        JwtService service = contexto.getBean(JwtService.class);
        Lojista lojista = Lojista.builder().login("fulano").build();
        String token = service.gerarToken(lojista);
        System.out.println(token);

        boolean isTokenValido = service.tokenValido(token);
        System.out.println("O token está válido? " + isTokenValido);
        System.out.println(service.obterLoginUsuario(token));
    }

}
