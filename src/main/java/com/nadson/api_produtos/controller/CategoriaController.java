package com.nadson.api_produtos.controller;

import com.nadson.api_produtos.dto.CategoriaRequest;
import com.nadson.api_produtos.dto.CategoriaResponse;
import com.nadson.api_produtos.exception.ResourceNotFoundException;
import com.nadson.api_produtos.mapper.CategoriaMapper;
import com.nadson.api_produtos.model.Categoria;
import com.nadson.api_produtos.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @Autowired
    private CategoriaMapper mapper;


    @GetMapping
    public List<CategoriaResponse> listarTodas(){
        return service.listarTodos()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> buscar(@PathVariable Long id){
        return service.buscarPorId(id)
                .map(categoria -> ResponseEntity.ok(mapper.toResponse(categoria)))
                .orElseThrow(() -> new ResourceNotFoundException("Categoria com ID" + id + " não encontrada!"));
    }

    @PostMapping
    public CategoriaResponse criar(@Valid @RequestBody CategoriaRequest request){
        Categoria categoria = mapper.toEntity(request);
        Categoria salva = service.salvar(categoria);
        return mapper.toResponse(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaRequest request
    ){
        return service.buscarPorId(id)
                .map(categoriaExistente -> {
                    categoriaExistente.setNome(request.getNome());
                    categoriaExistente.setDescricao(request.getDescricao());

                    Categoria atualizada = service.salvar(categoriaExistente);

                    return ResponseEntity.ok(mapper.toResponse(atualizada));
                })
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
