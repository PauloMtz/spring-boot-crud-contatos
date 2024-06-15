package com.contatos.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.contatos.domain.model.Contato;

public interface ApiContatoRepository extends JpaRepository<Contato, Long> {
    
    // join fetch para resolver o problema de vários selects gerados pela JPA
    @Query("from Contato c join fetch c.endereco")
    List<Contato> listarContatos();
}
