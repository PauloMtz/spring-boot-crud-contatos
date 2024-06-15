package com.contatos.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.contatos.api.model.inputDto.ContatoInputDto;
import com.contatos.domain.model.Contato;
//import com.contatos.domain.model.Endereco;

@Component
public class ContatoDtoDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public Contato convertToDomainObject(ContatoInputDto inputDto) {

        // todo esse código é substituído pelo ModelMapper
        /*Contato contato = new Contato();
        contato.setNome(inputDto.getNome());
        contato.setCpf(inputDto.getCpf());
        contato.setEmail(inputDto.getEmail());
        contato.setTelefone(inputDto.getTelefone());

        Endereco endereco = new Endereco();
        endereco.setId(inputDto.getEnderecoInput().getId());

        contato.setEndereco(endereco);

        return contato;*/

        return modelMapper.map(inputDto, Contato.class);
    }
}
