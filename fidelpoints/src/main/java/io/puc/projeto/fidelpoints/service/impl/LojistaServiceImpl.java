package io.puc.projeto.fidelpoints.service.impl;

import io.puc.projeto.fidelpoints.domain.entity.Lojista;
import io.puc.projeto.fidelpoints.domain.enums.Role;
import io.puc.projeto.fidelpoints.domain.repository.LojistaRepository;
import io.puc.projeto.fidelpoints.exception.SenhaInvalidaException;
import io.puc.projeto.fidelpoints.service.LojistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LojistaServiceImpl implements LojistaService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LojistaRepository lojistaRepository;

    @Override
    @Transactional
    public Lojista salvarLojista(Lojista lojista){

            String senhaCriptografada = passwordEncoder.encode(lojista.getSenha());
            lojista.setSenha(senhaCriptografada);

            lojista.setRole(Role.LOJISTA);

        return lojistaRepository.save(lojista);
    }


}
