package com.br.backend.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.backend.entity.Usuario;
import com.br.backend.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos() {
        List<Usuario> resposta = service.todosUsuarios();
        return ResponseEntity.ok(resposta);
    }

    @PostMapping
    public ResponseEntity<Void> novoUsuario(@RequestBody Usuario usuario, UriComponentsBuilder ucb) {
        Usuario usuarioCadastrado = service.novoUsuario(usuario);   
        URI localizacaoDoNovoUsuario = ucb
                .path("usuario/{id}")
                .buildAndExpand(usuarioCadastrado.getId())
                .toUri();     
        return ResponseEntity.created(localizacaoDoNovoUsuario).build(); 
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Usuario>> buscarPorId(@PathVariable("id") Long id){
        Optional<Usuario> resposta = service.buscarPorId(id);
        if (resposta.isEmpty()){  
            return ResponseEntity.notFound().build();          
        } else {
            return ResponseEntity.ok(resposta);
        }       
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> atualizarUsuarioComPut(@PathVariable Long id, @RequestBody Usuario usuarioAtualizacao) { 
        Optional<Usuario> buscaUsuario = service.buscarPorId(id); 
        if(buscaUsuario.isEmpty()){            
            return ResponseEntity.notFound().build();
        } else {
            service.mudarUsuarioPorId(id, usuarioAtualizacao);
            return ResponseEntity.noContent().build();
        }       
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        Optional<Usuario> buscaUsuario = service.buscarPorId(id); 
        if(buscaUsuario.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            service.deleteUsuarioPorId(id);
            return ResponseEntity.noContent().build();
        }        
    }
    
}
