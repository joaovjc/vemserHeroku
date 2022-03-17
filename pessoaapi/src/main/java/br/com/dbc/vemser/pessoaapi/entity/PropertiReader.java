package br.com.dbc.vemser.pessoaapi.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiReader {
	
	@Value("${ambiente}")
	private String ambiente;

	public String getAmbiente() {
		return ambiente;
	}
}
