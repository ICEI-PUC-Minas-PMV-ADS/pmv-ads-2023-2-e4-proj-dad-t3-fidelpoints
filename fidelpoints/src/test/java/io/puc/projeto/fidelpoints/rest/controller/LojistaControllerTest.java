package io.puc.projeto.fidelpoints.rest.controller;

import io.puc.projeto.fidelpoints.domain.entity.Cliente;
import io.puc.projeto.fidelpoints.domain.entity.Lojista;
import io.puc.projeto.fidelpoints.domain.repository.ClientesRepository;
import io.puc.projeto.fidelpoints.domain.repository.LojistaRepository;
import io.puc.projeto.fidelpoints.exception.RegraNegocioException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LojistaControllerTest {

    @InjectMocks
    LojistaController lojistaController;

    @Mock
    LojistaRepository lojistaRepository;

    MockMvc mockMvc;



    @Test
    void salvarLojistaExpectedException() {
        Lojista lojistaZe = Lojista.builder()
                .build();

        Exception exception = assertThrows(RegraNegocioException.class, () -> {
            lojistaController.salvar(lojistaZe);
        });

        String expectedMessage = "O nome do cliente nao foi informado";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}