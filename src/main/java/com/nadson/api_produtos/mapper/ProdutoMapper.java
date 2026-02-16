package com.nadson.api_produtos.mapper;

import com.nadson.api_produtos.dto.ProdutoRequest;
import com.nadson.api_produtos.dto.ProdutoResponse;
import com.nadson.api_produtos.exception.ResourceNotFoundException;
import com.nadson.api_produtos.model.Categoria;
import com.nadson.api_produtos.model.Produto;
import com.nadson.api_produtos.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    @Autowired
    private CategoriaService categoriaService;

    public Produto toEntity(ProdutoRequest request){
        Produto produto = new Produto();
        produto.setNome(request.getNome());
        produto.setPreco(request.getPreco());
        produto.setEstoque(request.getEstoque());

        Categoria categoria = categoriaService.buscarPorId(request.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada!"));
        produto.setCategoria(categoria);

        return produto;
    }

    public ProdutoResponse toResponse(Produto produto){
        ProdutoResponse response = new ProdutoResponse();
        response.setId(produto.getId());
        response.setNome(produto.getNome());
        response.setPreco(produto.getPreco());
        response.setEstoque(produto.getEstoque());
        response.setCriadoEm(produto.getCriadoEm());

        if(produto.getCategoria() != null){
            response.setCategoriaId(produto.getCategoria().getId());
            response.setCategoriaNome(produto.getCategoria().getNome());
        }

        return response;
    }

}
