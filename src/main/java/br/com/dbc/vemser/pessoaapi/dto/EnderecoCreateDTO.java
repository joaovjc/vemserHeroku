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
public class EnderecoCreateDTO {
	
	@NotNull
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
