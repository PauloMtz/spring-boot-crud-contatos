package com.contatos.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.contatos.api.model.dto.ContatoDto;
import com.contatos.api.model.dto.EnderecoDto;
import com.contatos.domain.model.Contato;

@Component
public class ContatoDtoAssembler {
    
    public ContatoDto convertToDto(Contato contato) {

        EnderecoDto enderecoDto = new EnderecoDto();
        enderecoDto.setId(contato.getEndereco().getId());
        enderecoDto.setCidade(contato.getEndereco().getCidade());
        enderecoDto.setEstado(contato.getEndereco().getEstado());

        ContatoDto contatoDto = new ContatoDto();
        contatoDto.setId(contato.getId());
        contatoDto.setNome(contato.getNome());
        contatoDto.setTelefone(contato.getTelefone());
        contatoDto.setEndereco(enderecoDto);

        return contatoDto;
    }

    public List<ContatoDto> convertToCollectionDto(List<Contato> contatos) {

        return contatos.stream()
            .map(contato -> convertToDto(contato))
            .collect(Collectors.toList());
    }
}
