package com.nadson.api_produtos.mapper;

import com.nadson.api_produtos.dto.CategoriaRequest;
import com.nadson.api_produtos.dto.CategoriaResponse;
import com.nadson.api_produtos.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public Categoria toEntity(CategoriaRequest request){
        Categoria categoria = new Categoria();
        categoria.setNome(request.getNome());
        categoria.setDescricao(request.getDescricao());
        return categoria;
    }

    public CategoriaResponse toResponse(Categoria categoria){
        CategoriaResponse response = new CategoriaResponse();
        response.setId(categoria.getId());
        response.setNome(categoria.getNome());
        response.setDescricao(categoria.getDescricao());
        return response;
    }
}