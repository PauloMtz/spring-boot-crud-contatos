package com.contatos.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.contatos.api.model.dto.EnderecoDto;
import com.contatos.domain.model.Endereco;

@Component
public class EnderecoDtoAssembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public EnderecoDto convertToDto(Endereco endereco) {

        return modelMapper.map(endereco, EnderecoDto.class);
    }

    public List<EnderecoDto> convertToCollectionDto(List<Endereco> enderecos) {

        return enderecos.stream()
            .map(endereco -> convertToDto(endereco))
            .collect(Collectors.toList());
    }
}
