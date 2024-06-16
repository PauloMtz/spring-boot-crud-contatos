package com.contatos.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.contatos.api.repository.ApiContatoRepository;
import com.contatos.core.exception.RegistroNaoEncontradoException;
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

    public Contato buscar(Long contatoId) {
        return contatoRepository.findById(contatoId)
            .orElseThrow(() -> new RegistroNaoEncontradoException("Contato não encontrado"));
    }

    /*
     * o método deleteById do spring já é anotado com @Transactional,
     * isso faz com que ele gerencie as exceções da operação.
     * Ao anotar esse método excluir, as exceções aqui não são
     * mais capturadas por este método. Para voltar a capturar, deve-se
     * forçar a JPA a executar as alterações no banco de dados,
     * utilizando o método flush() 
     */
    @Transactional
    public void excluir(Long contatoId) {

        try {
            contatoRepository.deleteById(contatoId);
            contatoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new RegistroNaoEncontradoException("Contato não encontrado");
        }
    }
}
