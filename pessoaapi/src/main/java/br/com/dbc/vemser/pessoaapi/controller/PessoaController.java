package br.com.dbc.vemser.pessoaapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.dbc.vemser.pessoaapi.dto.PessoaCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.PessoaDTO;
import br.com.dbc.vemser.pessoaapi.service.PessoaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pessoa") // localhost:8080/pessoa
@Validated
public class PessoaController {
	
	@Autowired
    private PessoaService pessoaService;

//    @GetMapping("/hello") // localhost:8080/pessoa/hello
//    public String hello() {
//        return "Hello world!";
//    }
	
	@ApiOperation(value = "Cria uma pessoas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a pessoas criada"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping // localhost:8080/pessoa
    public ResponseEntity<PessoaDTO> create(@Valid @RequestBody PessoaCreateDTO pessoa) {
        return ResponseEntity.ok(pessoaService.create(pessoa));
    }

    @ApiOperation(value = "Retorna uma lista de pessoas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de pessoas"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping // localhost:8080/pessoa
    public List<PessoaDTO> list() {
        return pessoaService.list();
    }
    
    @ApiOperation(value = "Retorna a pessoa com o nome passado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a pessoas com o id passado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/byname") // localhost:8080/pessoa/byname?nome=Rafa
    public List<PessoaDTO> listByName(@RequestParam("nome") String nome) {
        return pessoaService.listByName(nome);
    }
    
    @ApiOperation(value = "Retorna a pessoa atualizada")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a pessoas atualizada"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping("/{idPessoa}") // localhost:8080/pessoa/1000
    public ResponseEntity<PessoaDTO> update(@PathVariable("idPessoa") Integer id,
    		@Valid @RequestBody PessoaCreateDTO pessoaAtualizar){
        return ResponseEntity.ok(pessoaService.update(id, pessoaAtualizar));
    }
    
    @ApiOperation(value = "Deleta a pessoa com o id passado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "A pessoa é deletada"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/{idPessoa}") // localhost:8080/pessoa/10
    public void delete(@PathVariable("idPessoa") Integer id){
        pessoaService.delete(id);
    }
}
