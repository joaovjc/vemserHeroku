package br.com.dbc.vemser.pessoaapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.dbc.vemser.pessoaapi.dto.ContatoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.ContatoDTO;
import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.ContatoRepository;
import br.com.dbc.vemser.pessoaapi.repository.PessoaRepository;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ContatoService {
	
	@Autowired
    private ContatoRepository ContatoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private ObjectMapper objectMapper;

    public ContatoDTO create(ContatoCreateDTO contatoCreateDTO){
    	if(pessoaRepository.idExists(contatoCreateDTO.getIdContato())== false)new RegraDeNegocioException("Não existe uma pessoa com esse id");
    	
    	Contato contato = objectMapper.convertValue(contatoCreateDTO, Contato.class);
		
		log.info("Criando contato");
		Contato contatoCriado = ContatoRepository.create(contato);
		
		ContatoDTO contatoDTO = objectMapper.convertValue(contatoCriado, ContatoDTO.class);
    	
        return contatoDTO;
    }

    public List<ContatoDTO> list(){
        return ContatoRepository.list()
        		.stream()
        		.map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
        		.collect(Collectors.toList());
    }

    public ContatoDTO update(Integer id,
    		ContatoCreateDTO contatoAtualizar){
    	Contato contato = objectMapper.convertValue(contatoAtualizar, Contato.class);
		
		log.info("atualizando o contato");
		Contato contatoCriado = ContatoRepository.update(id, contato);
		
		ContatoDTO contatoDTO = objectMapper.convertValue(contatoCriado, ContatoDTO.class);
    	
        return contatoDTO;
    }

    public void delete(Integer id){
        ContatoRepository.delete(id);
    }

    public List<ContatoDTO> listByIdPessoa(long id) {
        return ContatoRepository.listByIdPessoa(id).stream()
        		.map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
        		.collect(Collectors.toList());
    }
}
