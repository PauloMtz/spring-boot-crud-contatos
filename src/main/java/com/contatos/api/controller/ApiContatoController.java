package com.contatos.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.contatos.api.assembler.ContatoDtoAssembler;
import com.contatos.api.assembler.ContatoDtoDisassembler;
import com.contatos.api.model.dto.ContatoDto;
import com.contatos.api.model.inputDto.ContatoInputDto;
import com.contatos.api.repository.ApiContatoRepository;
import com.contatos.api.service.ApiContatoService;
import com.contatos.core.exception.RegistroNaoEncontradoException;
import com.contatos.domain.model.Contato;
import com.contatos.domain.repository.ContatoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contato")
public class ApiContatoController {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private ApiContatoRepository apiContatoRepository;

    @Autowired
    private ApiContatoService contatoService;

    @Autowired
    private ContatoDtoAssembler assembler;

    @Autowired
    private ContatoDtoDisassembler disassembler;
    
    // localhost:8080/api/contato/listar
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
        return assembler.convertToCollectionDto(contatoRepository.findAll());
    }

    // localhost:8080/api/contato/listar-contatos
    @GetMapping("/listar-contatos")
    public List<ContatoDto> listarContatos() {

        // faz apenas um select
        return assembler.convertToCollectionDto(apiContatoRepository.listarContatos());
    }

    // localhost:8080/api/contato/cadastrar
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cadastrar")
    public ContatoDto adicionar(@RequestBody 
        @Valid ContatoInputDto inputDto) {

        try {
            Contato contato = disassembler.convertToDomainObject(inputDto);
            
            // a classe RestauranteService fica isolada das classes Dto
            return assembler.convertToDto(contatoService.salvar(contato));
        } catch (RegistroNaoEncontradoException e) {
            throw new RegistroNaoEncontradoException(e.getMessage());
        }
    }

    // localhost:8080/api/contato/buscar/1
    @GetMapping("/buscar/{contatoId}")
    public ContatoDto buscarPorId(@PathVariable("contatoId") Long id) {

        return assembler.convertToDto(contatoService.buscar(id));
    }

    // localhost:8080/api/contato/atualizar/1
    @PutMapping("/atualizar/{contatoId}")
    public ContatoDto atualizar(@PathVariable Long contatoId,
        @RequestBody @Valid ContatoInputDto inputDto) {

        try {
            Contato contato = contatoService.buscar(contatoId);
            disassembler.copyToDomainObject(inputDto, contato);
            contato = contatoService.salvar(contato);

            return assembler.convertToDto(contato);
        } catch (RegistroNaoEncontradoException e) {
            throw new RegistroNaoEncontradoException("Contato não encontrado");
        }
    }

    // localhost:8080/api/contato/excluir/1
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/excluir/{contatoId}")
	public void remover(@PathVariable Long contatoId) {
        contatoService.excluir(contatoId);
	}

    // esses métodos foram movidos para uma classe assembler
    /*private ContatoDto convertToDto(Contato contato) {

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
    }*/

    // esse método foi movido para uma classe disassembler
    /*private Contato convertToDomainObject(ContatoInputDto inputDto) {
        Contato contato = new Contato();
        contato.setNome(inputDto.getNome());
        contato.setCpf(inputDto.getCpf());
        contato.setEmail(inputDto.getEmail());
        contato.setTelefone(inputDto.getTelefone());

        Endereco endereco = new Endereco();
        endereco.setId(inputDto.getEnderecoInput().getId());

        contato.setEndereco(endereco);

        return contato;
    }*/
}
