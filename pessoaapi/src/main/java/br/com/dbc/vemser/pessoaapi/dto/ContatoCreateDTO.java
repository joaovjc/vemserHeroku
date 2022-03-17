package br.com.dbc.vemser.pessoaapi.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ContatoCreateDTO {
	
	@NotNull
	private Integer idContato;
	@NotNull
	private String tipoContato;
	@NotEmpty
	@Max(value = 13)
	private String numero;
	@NotEmpty
	private String descricao;
	
}
