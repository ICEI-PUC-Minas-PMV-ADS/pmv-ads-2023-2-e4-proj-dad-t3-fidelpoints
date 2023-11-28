package io.puc.projeto.fidelpoints.service.impl;

import io.puc.projeto.fidelpoints.domain.entity.Lojista;
import io.puc.projeto.fidelpoints.domain.repository.LojistaRepository;
import io.puc.projeto.fidelpoints.exception.SenhaInvalidaException;
import io.puc.projeto.fidelpoints.service.LojistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LojistaServiceImpl implements UserDetailsService, LojistaService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LojistaRepository lojistaRepository;

    @Override
    @Transactional
    public Lojista salvarLojista(Lojista lojista){

            String senhaCriptografada = passwordEncoder.encode(lojista.getSenha());
            lojista.setSenha(senhaCriptografada);

        return lojistaRepository.save(lojista);
    }

    public UserDetails autenticar (Lojista usuario) {
        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = passwordEncoder.matches(usuario.getSenha(), user.getPassword());

        if (senhasBatem) {
            return user;
        }
        throw new SenhaInvalidaException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Lojista lojista = lojistaRepository
                .findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado. "));

        boolean roles = lojista.isAdmin(); // ?
         //       new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(lojista.getLogin())
                .password(lojista.getSenha())
                .roles("ADMIN")
                .build();
    }
}
