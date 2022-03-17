package br.com.dbc.vemser.pessoaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class ContatoDTO extends ContatoCreateDTO{
	
	private Integer id;

}
