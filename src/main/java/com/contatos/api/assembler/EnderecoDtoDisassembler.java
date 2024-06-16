package com.contatos.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.contatos.api.model.inputDto.EnderecoInputDto;
import com.contatos.domain.model.Endereco;

import jakarta.validation.Valid;

@Component
public class EnderecoDtoDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public Endereco convertToDomainObject(@Valid EnderecoInputDto inputDto) {

        return modelMapper.map(inputDto, Endereco.class);
    }

    public void copyToDomainObject(EnderecoInputDto inputDto, Endereco endereco) {
        modelMapper.map(inputDto, endereco);
    }
}
