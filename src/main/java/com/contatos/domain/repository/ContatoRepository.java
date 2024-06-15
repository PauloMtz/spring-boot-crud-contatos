package com.contatos.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.contatos.domain.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    @Query("select count(*) from Contato")
    long countByNome();

    // join fetch para resolver o problema de vários selects gerados pela JPA
    @Query("from Contato c join fetch c.endereco")
    Page<Contato> listarContatos(Pageable pageable);

    // join fetch para resolver o problema de vários selects gerados pela JPA
    @Query("from Contato c join fetch c.endereco where upper(c.nome) like concat('%', upper(:nome), '%')")
    Page<Contato> findByNome(@Param("nome") String nome, Pageable pageable);

    Optional<Contato> findByCpf(String cpf);
    Optional<Contato> findByEmail(String email);

    default Boolean isCpfCadastrado(Contato contato) {
        if (contato.getCpf() == null) {
            return false;
        }

        return findByCpf(contato.getCpf())
            .map(cpfEncontrado -> !cpfEncontrado
            .getId().equals(contato.getId()))
            .orElse(false);
    }
}
