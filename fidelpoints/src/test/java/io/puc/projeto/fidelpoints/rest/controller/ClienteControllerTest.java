package io.puc.projeto.fidelpoints.rest.controller;

import io.puc.projeto.fidelpoints.domain.entity.Cliente;
import io.puc.projeto.fidelpoints.domain.repository.ClientesRepository;
import io.puc.projeto.fidelpoints.exception.RegraNegocioException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {

    @InjectMocks
    ClienteController clienteController;

    @Mock
    ClientesRepository clientesRepository;

    MockMvc mockMvc;

    @Test
    void salvarCliente() throws Exception{
        Cliente cliente = Cliente.builder()
                .nome("cliente")
                .celular("11950501020")
                .build();
        when(clientesRepository.save(any())).thenReturn(
                Cliente.builder().id(1).build());

        Cliente clienteReturn = clienteController.salvar(cliente);

        assertEquals(1, clienteReturn.getId().intValue());

    }


    @Test
    void salvarClienteExpectedException() {
        Cliente clienteZe = Cliente.builder()
                .build();

        Exception exception = assertThrows(RegraNegocioException.class, () -> {
            clienteController.salvar(clienteZe);
        });

        String expectedMessage = "O nome do cliente nao foi informado";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}