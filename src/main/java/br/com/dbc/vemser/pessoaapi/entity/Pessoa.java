package br.com.dbc.vemser.pessoaapi.entity;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Pessoa {
    private Integer idPessoa;
	@NotEmpty
    private String nome;
	@Past
    private LocalDate dataNascimento;
	@CPF
    private String cpf;
}