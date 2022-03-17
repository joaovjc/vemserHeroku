package br.com.dbc.vemser.pessoaapi.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PessoaCreateDTO {
	@NotEmpty
    private String nome;
	@Past
    private LocalDate dataNascimento;
	@CPF
    private String cpf;
}
