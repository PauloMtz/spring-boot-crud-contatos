package com.contatos.api.model.inputDto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInputDto {
    
    @NotNull
    private Long id;
}
