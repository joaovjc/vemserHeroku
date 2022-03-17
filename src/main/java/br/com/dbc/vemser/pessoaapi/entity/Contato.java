package br.com.dbc.vemser.pessoaapi.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
public class Contato {
	
	private Integer id;
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
