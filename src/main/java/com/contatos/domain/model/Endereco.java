package com.contatos.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Informe o CEP")
    @Column(length = 9, name = "cep")
	private String cep;
    
	@NotBlank(message = "O campo endereço é obrigatório")
    @Column(length = 60, name = "endereco")
	private String endereco;

	@Column(length = 60, name = "complemento")
	private String complemento;
	
	@NotBlank(message = "O campo bairro é obrigatório")
	@Column(length = 60, name = "bairro")
	private String bairro;

	@NotBlank(message = "O campo cidade é obrigatório")
    @Column(length = 60, name = "cidade")
	private String cidade;

	@NotBlank(message = "O campo estado é obrigatório")
    @Column(length = 2, name = "estado")
	private String estado;

	/*@OneToOne(mappedBy = "endereco")
    private Contato contato;*/
}
