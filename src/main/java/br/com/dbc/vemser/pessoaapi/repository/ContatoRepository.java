package br.com.dbc.vemser.pessoaapi.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;

@Repository
public class ContatoRepository {
    private static List<Contato> listaContatos = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public ContatoRepository() {
        listaContatos.add(new Contato(COUNTER.incrementAndGet(),1,"comercial","451516541651","descricao"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(),2,"comercial","451516541651","descricao"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(),3,"comercial","451516541651","descricao"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(),4,"comercial","451516541651","descricao"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(),5,"comercial","451516541651","descricao"));
    }
    
    public boolean idExists(Integer id) {
    	return  listaContatos.stream()
                .filter(contato -> contato.getId().equals(id))
                .findFirst().isPresent();
    }
    
    public Contato create(Contato contato) {
    	contato.setId(COUNTER.incrementAndGet());
        listaContatos.add(contato);
        return contato;
    }

    public List<Contato> list() {
        return listaContatos;
    }

    public Contato update(Integer id,
                         Contato ContatoAtualizar){
    	if(idExists(id) == false)new RegraDeNegocioException("contato não econtrado");
        Contato contatoRecuperado = listaContatos.stream()
                .filter(contato -> contato.getId().equals(id))
                .findFirst().get();
        contatoRecuperado.setDescricao(ContatoAtualizar.getDescricao());
        contatoRecuperado.setNumero(ContatoAtualizar.getNumero());
        contatoRecuperado.setTipoContato(ContatoAtualizar.getTipoContato());
        return contatoRecuperado;
    }

    public void delete(Integer id){
    	if(idExists(id) == false)new RegraDeNegocioException("contato não econtrado");
        Contato contatoRecuperado = listaContatos.stream()
                .filter(contato -> contato.getId().equals(id))
                .findFirst().get();
        listaContatos.remove(contatoRecuperado);
    }

    public List<Contato> listByIdPessoa(long id) {
        return listaContatos.stream()
                .filter(contato -> contato.getIdContato()==id)
                .collect(Collectors.toList());
    }
}
