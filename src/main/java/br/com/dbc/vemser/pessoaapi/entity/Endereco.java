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
public class Endereco {

	private Integer id;
	private Integer idPessoa;
	@NotNull
	private String tipoEndereco;
	@NotEmpty
	@Max(250)
	private String logadouro;
	@NotEmpty
	private String numero;
	@NotEmpty
	@Max(8)
	private String cep;
	@NotEmpty
	@Max(250)
	private String cidade;
	@NotNull
	private String estado;
	@NotNull
	private String pais;
	
}
