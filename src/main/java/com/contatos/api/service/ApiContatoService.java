package com.contatos.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contatos.api.repository.ApiContatoRepository;
import com.contatos.domain.model.Contato;
import com.contatos.domain.model.Endereco;

@Service
public class ApiContatoService {
    
    @Autowired
    private ApiContatoRepository contatoRepository;

    @Autowired
    private ApiEnderecoService enderecoService;

    public Contato salvar(Contato contato) {

        Long enderecoId = contato.getEndereco().getId();
        Endereco endereco = enderecoService.buscar(enderecoId);

        contato.setEndereco(endereco);
        
        return contatoRepository.save(contato);
    }
}
