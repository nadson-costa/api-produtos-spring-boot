package com.nadson.api_produtos.controller;

import com.nadson.api_produtos.dto.ProdutoRequest;
import com.nadson.api_produtos.dto.ProdutoResponse;
import com.nadson.api_produtos.exception.ResourceNotFoundException;
import com.nadson.api_produtos.mapper.ProdutoMapper;
import com.nadson.api_produtos.model.Produto;
import com.nadson.api_produtos.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @Autowired
    private ProdutoMapper mapper;


    @GetMapping
    public List<ProdutoResponse> listarTodos(){
        return service.listarTodos()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscar(@PathVariable Long id){
        return service.buscarPorId(id)
                .map(produto -> ResponseEntity.ok(mapper.toResponse(produto)))
                .orElseThrow(() -> new ResourceNotFoundException("Produto com ID " + id + " não foi encontrado"));
    }

    @PostMapping
    public ProdutoResponse criar(@Valid @RequestBody ProdutoRequest request){
        Produto produto = mapper.toEntity(request);
        Produto salvo = service.salvar(produto);
        return mapper.toResponse(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(
            @PathVariable Long id, @Valid @RequestBody ProdutoRequest request
    ){
        return service.buscarPorId(id)
                .map(produtoExistente -> {
                    produtoExistente.setNome(request.getNome());
                    produtoExistente.setPreco(request.getPreco());
                    produtoExistente.setEstoque(request.getEstoque());

                    Produto atualizado = service.salvar(produtoExistente);

                    return ResponseEntity.ok(mapper.toResponse(atualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
