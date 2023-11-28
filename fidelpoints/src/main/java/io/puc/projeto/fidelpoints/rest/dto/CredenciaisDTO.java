package io.puc.projeto.fidelpoints.rest.dto;

import io.puc.projeto.fidelpoints.domain.enums.RoleEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
public class CredenciaisDTO {

    @Email
    private String login;

    private String senha;

    private RoleEnum roleEnum;

}
