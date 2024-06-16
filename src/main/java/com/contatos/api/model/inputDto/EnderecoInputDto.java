package com.contatos.api.model.inputDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInputDto {
    
    @NotBlank
	private String cep;
    
	@NotBlank
	private String endereco;

	private String complemento;
	
	@NotBlank
	private String bairro;

	@NotBlank
	private String cidade;

	@NotBlank
	private String estado;
}
