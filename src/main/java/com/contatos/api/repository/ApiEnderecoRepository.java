package com.contatos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contatos.domain.model.Endereco;

public interface ApiEnderecoRepository extends JpaRepository<Endereco, Long> {
}
