package com.contatos.api.model.inputDto;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContatoInputDto {
    
    @NotBlank
    private String nome;

    @CPF
    @NotNull
    private String cpf;

    @NotBlank
    private String email;

    @NotNull
    private String telefone;

    @Valid
	@NotNull
    private EnderecoIdInputDto enderecoInput;
}
