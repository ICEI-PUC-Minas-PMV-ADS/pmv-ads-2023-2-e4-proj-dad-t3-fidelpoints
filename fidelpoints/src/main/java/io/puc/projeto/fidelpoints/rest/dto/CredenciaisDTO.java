package io.puc.projeto.fidelpoints.rest.dto;

import io.puc.projeto.fidelpoints.domain.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;

@Data
@NoArgsConstructor
public class CredenciaisDTO {

    @Email
    private String login;

    private String senha;

    private Role roleEnum;

}
