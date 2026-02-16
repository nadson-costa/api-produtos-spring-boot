package com.nadson.api_produtos.mapper;

import com.nadson.api_produtos.dto.ProdutoRequest;
import com.nadson.api_produtos.dto.ProdutoResponse;
import com.nadson.api_produtos.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {
    public Produto toEntity(ProdutoRequest request){
        Produto produto = new Produto();
        produto.setNome(request.getNome());
        produto.setPreco(request.getPreco());
        produto.setEstoque(request.getEstoque());
        return produto;
    }

    public ProdutoResponse toResponse(Produto produto){
        ProdutoResponse response = new ProdutoResponse();
        response.setId(produto.getId());
        response.setNome(produto.getNome());
        response.setPreco(produto.getPreco());
        response.setEstoque(produto.getEstoque());
        response.setCriadoEm(produto.getCriadoEm());
        return response;
    }

}
