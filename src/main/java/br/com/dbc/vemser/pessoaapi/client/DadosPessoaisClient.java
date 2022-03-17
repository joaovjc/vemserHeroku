package br.com.dbc.vemser.pessoaapi.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

import br.com.dbc.vemser.pessoaapi.dto.DadosPessoaisDTO;
import feign.Headers;
import feign.Param;
import feign.RequestLine;


@FeignClient(value="dados-pessoais-api", url="https://dados-pessoais-vemserdbc.herokuapp.com/%22")
@Headers("Content-Type: application/json")
public interface DadosPessoaisClient {
	
    @RequestLine("GET /dados-pessoais")
    List<DadosPessoaisDTO> getAll();

    @RequestLine("POST /dados-pessoais")
    DadosPessoaisDTO post(DadosPessoaisDTO dadosPessoaisDTO);

    @RequestLine("PUT /dados-pessoais/{cpf}")
    DadosPessoaisDTO put(@Param("cpf") String cpf, DadosPessoaisDTO dadosPessoaisDTO);

    @RequestLine("DELETE /dados-pessoais/{cpf}")
    void delete(@Param("cpf") String cpf);

    @RequestLine("GET /dados-pessoais/{cpf}")
    DadosPessoaisDTO get(@Param("cpf") String cpf);
}