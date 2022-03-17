package br.com.dbc.vemser.pessoaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dbc.vemser.pessoaapi.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.dbc.vemser.pessoaapi.service.EnderecoService;

@RestController
@RequestMapping("/endereco") // localhost:8080/endereco
public class EnderecoController {
	
	@Autowired
    private EnderecoService enderecoService;

    @PostMapping // localhost:8080/endereco
    public EnderecoDTO create(@RequestBody EnderecoCreateDTO endereco) {
        return enderecoService.create(endereco);
    }

    @GetMapping // localhost:8080/endereco
    public List<EnderecoDTO> list() {
        return enderecoService.list();
    }
    
    @GetMapping("/{byid}") // localhost:8080/Endereco/10
    public List<EnderecoDTO> listByid(@PathVariable("byid") Integer id) {
        return enderecoService.listByIdPessoa(id);
    }
    
    @GetMapping("/{byidpessoa}/pessoa") // localhost:8080/Endereco/100/pessoa
    public List<EnderecoDTO> listByidPessoa(@PathVariable("byidpessoa") long byidpessoa) {
        return enderecoService.listByIdPessoa(byidpessoa);
    }

    @PutMapping("/{idendereco}") // localhost:8080/Endereco/1000
    public EnderecoDTO update(@PathVariable("idendereco") Integer id,
                         @RequestBody EnderecoCreateDTO enderecoAtualizar){
        return enderecoService.update(id, enderecoAtualizar);
    }

    @DeleteMapping("/{idendereco}") // localhost:8080/endereco/10
    public void delete(@PathVariable("idendereco") Integer id){
        enderecoService.delete(id);
    }
}
