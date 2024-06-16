package com.contatos.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.contatos.api.repository.ApiEnderecoRepository;
import com.contatos.core.exception.RegistroNaoEncontradoException;
import com.contatos.domain.model.Endereco;

@Service
public class ApiEnderecoService {
    
    @Autowired
    private ApiEnderecoRepository enderecoRepository;

    public Endereco buscar(Long enderecoId) {

        return enderecoRepository.findById(enderecoId)
            .orElseThrow(() -> new RegistroNaoEncontradoException("Endereço não encontrado"));
    }

    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public void excluir(Long enderecoId) {

        try {
            enderecoRepository.deleteById(enderecoId);
        } catch (EmptyResultDataAccessException e) {
            throw new RegistroNaoEncontradoException("Endereço não encontrado");
        }
    }
}
