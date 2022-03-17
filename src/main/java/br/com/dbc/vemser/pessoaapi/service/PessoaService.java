package br.com.dbc.vemser.pessoaapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.dbc.vemser.pessoaapi.dto.PessoaCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.PessoaDTO;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.repository.PessoaRepository;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PessoaService {
	
	@Autowired
    private PessoaRepository pessoaRepository;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private EmailService emailService;

	public PessoaDTO create(PessoaCreateDTO pessoaCreate){

		Pessoa pessoa = objectMapper.convertValue(pessoaCreate, Pessoa.class);
		
		log.info("Criando pessoa");
		Pessoa pessoaCriada = pessoaRepository.create(pessoa);
		
		PessoaDTO pessoaDTO = objectMapper.convertValue(pessoaCriada, PessoaDTO.class);
		
		emailService.sendEmail(pessoaDTO, "create");
		
		return pessoaDTO;
    }

    public List<PessoaDTO> list(){
        return pessoaRepository.list()
        		.stream()
        		.map(pessoa -> objectMapper.convertValue(pessoa, PessoaDTO.class))
        		.collect(Collectors.toList());
    }
	
    public PessoaDTO update(Integer id,
    		PessoaCreateDTO pessoaAtualizar){
    	Pessoa pessoa = objectMapper.convertValue(pessoaAtualizar, Pessoa.class);
    	
        Pessoa update = pessoaRepository.update(id, pessoa);
        
        PessoaDTO pessoaDTO = objectMapper.convertValue(update, PessoaDTO.class);
        
        emailService.sendEmail(pessoaDTO, "update");
        
        return pessoaDTO;
    }

    public void delete(Integer id) {
    	
        Pessoa delete = pessoaRepository.delete(id);
        
        PessoaDTO pessoaDTO = objectMapper.convertValue(delete, PessoaDTO.class);
        
        emailService.sendEmail(pessoaDTO, "delete");
    }

    public List<PessoaDTO> listByName(String nome) {
        return pessoaRepository.listByName(nome)
        		.stream()
        		.map(pessoa -> objectMapper.convertValue(pessoa, PessoaDTO.class))
        		.collect(Collectors.toList());
    }
}
