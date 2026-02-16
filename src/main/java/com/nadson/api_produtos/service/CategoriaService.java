package com.nadson.api_produtos.service;

import com.nadson.api_produtos.model.Categoria;
import com.nadson.api_produtos.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> listarTodos(){
        return repository.findAll();
    }

    public Optional<Categoria> buscarPorId(Long id){
        return repository.findById(id);
    }

    public Categoria salvar(Categoria categoria){
        return repository.save(categoria);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }
}
