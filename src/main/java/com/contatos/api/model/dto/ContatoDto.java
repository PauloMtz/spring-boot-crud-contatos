package com.contatos.api.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContatoDto {
    
    private Long id;
    private String nome;
    private String telefone;
    private EnderecoDto endereco;
}
