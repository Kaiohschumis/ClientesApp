package com.kaiosx.cliente.rest;

import com.kaiosx.cliente.model.entity.Cliente;
import com.kaiosx.cliente.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("http://localhost:4200")
public class ClienteController {

    private ClienteRepository repository;

    @Autowired
    public ClienteController(ClienteRepository repository){
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar( @RequestBody Cliente cliente){
        return repository.save(cliente);
    }

    @GetMapping("{id}")
    public Cliente acharPorId( @PathVariable Integer id ){
        return  repository.findById(id).orElseThrow( ( ) -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        // pode ser utilizado tbm repository.deleteById(id);
        repository.findById(id).map(cliente -> {
            repository.delete(cliente);
            return Void.TYPE;
        })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado ) {
        repository.findById(id).map(cliente -> {
           cliente.setNome(clienteAtualizado.getNome());
           cliente.setCpf(clienteAtualizado.getCpf());
           return repository.save(cliente);
        })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
    }

    @GetMapping()
    public List<Cliente> findAll(){
        return  repository.findAll();
    }

}
