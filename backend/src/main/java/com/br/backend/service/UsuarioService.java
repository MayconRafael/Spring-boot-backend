package com.br.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.backend.entity.Usuario;
import com.br.backend.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;
    
    public List<Usuario> todosUsuarios() {    
        return repo.findAll();        
    }

    public Usuario novoUsuario(Usuario usuario){
        return repo.save(usuario); 
    }

    public Optional<Usuario> buscarPorId(Long id){
        return repo.findById(id);
    }

    public Usuario mudarUsuarioPorId(Long id, Usuario usuarioAtualizacao){     
        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setId(id);
        usuarioAtualizado.setNome(usuarioAtualizacao.getNome());
        usuarioAtualizado.setSenha(usuarioAtualizacao.getSenha());
        return repo.save(usuarioAtualizado);            
    }

    public void deleteUsuarioPorId(Long id){
        repo.deleteById(id);
    }   
    
}
