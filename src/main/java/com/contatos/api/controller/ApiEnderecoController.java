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

import com.contatos.api.assembler.EnderecoDtoAssembler;
import com.contatos.api.assembler.EnderecoDtoDisassembler;
import com.contatos.api.model.dto.EnderecoDto;
import com.contatos.api.model.inputDto.EnderecoInputDto;
import com.contatos.api.repository.ApiEnderecoRepository;
import com.contatos.api.service.ApiEnderecoService;
import com.contatos.domain.model.Endereco;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/endereco")
public class ApiEnderecoController {
    
    @Autowired
    private ApiEnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoDtoAssembler assembler;

    @Autowired
    private EnderecoDtoDisassembler disassembler;

    @Autowired
    private ApiEnderecoService enderecoService;

    // localhost:8080/api/contato/listar
    @GetMapping("/listar")
    public List<EnderecoDto> listar() {

        return assembler.convertToCollectionDto(enderecoRepository.findAll());
    }

    // localhost:8080/api/endereco/buscar/1
    @GetMapping("/buscar/{enderecoId}")
    public EnderecoDto buscarPorId(@PathVariable("enderecoId") Long id) {

        return assembler.convertToDto(enderecoService.buscar(id));
    }

    // localhost:8080/api/endereco/cadastrar
    @PostMapping("/cadastrar")
	@ResponseStatus(HttpStatus.CREATED)
	public EnderecoDto adicionar(@RequestBody 
		@Valid EnderecoInputDto inputDto) {

		Endereco endereco = disassembler.convertToDomainObject(inputDto);
		endereco = enderecoService.salvar(endereco);

		return assembler.convertToDto(endereco);
	}

    // localhost:8080/api/endereco/atualizar/1
    @PutMapping("/atualizar/{enderecoId}")
	public EnderecoDto atualizar(@PathVariable Long enderecoId, 
		@RequestBody @Valid EnderecoInputDto inputDto) {

		Endereco endereco = enderecoService.buscar(enderecoId);
			
		disassembler.copyToDomainObject(inputDto, endereco);

		endereco = enderecoService.salvar(endereco);

		return assembler.convertToDto(endereco);
	}

    // localhost:8080/api/endereco/excluir/1
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/excluir/{enderecoId}")
	public void remover(@PathVariable Long enderecoId) {
        enderecoService.excluir(enderecoId);
	}
}
