package com.contatos.domain.model;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "contatos")
public class Contato {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O campo nome é obrigatório")
    //@Size(min = 3, max = 60, message = "O campo nome deve ter entre {min} e {max} caracteres.")
	@Column(length = 60)
	private String nome;

    @CPF(message = "CPF inválido")
    @Column(nullable = false, unique = true, length = 11)
	private String cpf;

	@NotBlank(message = "O campo e-mail é obrigatório")
	@Column(nullable = false, unique = true, length = 60)
	private String email;
	
    @NotEmpty(message = "Informe um telefone")
	@Column(length = 14, name = "telefone")
	private String telefone;

    // valida os atributos de endereço
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
}
