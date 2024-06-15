package com.contatos.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import com.contatos.core.exception.NegocioException;
import com.contatos.core.exception.RegistroJaCadastradoException;
import com.contatos.core.exception.RegistroNaoEncontradoException;
import com.contatos.domain.model.Contato;
import com.contatos.domain.repository.ContatoRepository;

@Service
public class ContatoService {
    
    @Autowired
    private ContatoRepository contatoRepository;

    public void salvar(Contato contato) {
        Optional<Contato> emailExiste = contatoRepository.findByEmail(contato.getEmail());

        if (emailExiste.isPresent() && !emailExiste.get().equals(contato)) {
            var mensagem = "Este e-mail já está cadastrado.";
            var fieldError = new FieldError(contato.getClass().getName(),
                "email", contato.getEmail(), false, null, null, mensagem);
            
            throw new NegocioException(mensagem, fieldError);
        }
        validaCpfUnico(contato);
        contatoRepository.save(contato);
    }

    public Page<Contato> listarTodos(int numPage) {
        int size = 4;
        Pageable pageable = PageRequest.of(numPage -1, size, Sort.by("nome"));
		return contatoRepository.listarContatos(pageable);
	}

    public Contato buscarPorId(Long id) {
        var contatoEncontrado = contatoRepository.findById(id);

        if (contatoEncontrado.isPresent()) {
            return contatoEncontrado.get();
        }

        throw new RegistroNaoEncontradoException("Registro não encontrado.");
    }

    public void excluir(Long id) {
        var cargoEncontrado = buscarPorId(id);
        contatoRepository.delete(cargoEncontrado);
    }

    public Page<Contato> buscarPorNome(String nome, int numPage) {
        int size = 4;
        Pageable pageable = PageRequest.of(numPage -1, size, Sort.by("nome"));
        return contatoRepository.findByNome(nome, pageable);
    }

    private void validaCpfUnico(Contato contato) {
        if (contatoRepository.isCpfCadastrado(contato)) {
            var mensagem = "Este CPF já está cadastrado.";
            var fieldError = new FieldError(contato.getClass().getName(),
                "cpf", contato.getCpf(), false, null, null, mensagem);
            
            throw new RegistroJaCadastradoException(mensagem, fieldError);
        }
    }
}
