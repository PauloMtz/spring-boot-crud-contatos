package com.contatos.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contatos.api.model.dto.ContatoDto;
import com.contatos.api.model.dto.EnderecoDto;
import com.contatos.api.repository.ApiContatoRepository;
import com.contatos.domain.model.Contato;
import com.contatos.domain.repository.ContatoRepository;

@RestController
@RequestMapping("/api/converte/contato")
public class ApiContatoConverteController {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private ApiContatoRepository apiContatoRepository;
    
    @GetMapping("/listar")
    public List<ContatoDto> listar() {

        /*
         * relacionamentos @AlgumaCoisaToOne são EAGER
         * relacionamentos @AlgumaCoisaToMany são LAZY
         * o método findAll() em relacionamentos tem o problema N+1
         * se tiver 20 registros, efetua 20 selects
         * é preciso reescrever o método com @Query utilizando join fetch
         * e, se houver registro sem relação, deve-se utilizar left join fetch
        */

        // faz vários selects
        // localhost:8080/api/converte/contato/listar
        return convertToCollectionDto(contatoRepository.findAll());
    }

    @GetMapping("/listar-contatos")
    public List<ContatoDto> listarContatos() {

        // faz apenas um select
        // localhost:8080/api/converte/contato/listar-contatos
        return convertToCollectionDto(apiContatoRepository.listarContatos());
    }

    private ContatoDto convertToDto(Contato contato) {

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

    private List<ContatoDto> convertToCollectionDto(List<Contato> contatos) {

        return contatos.stream()
            .map(contato -> convertToDto(contato))
            .collect(Collectors.toList());
    }
}
