package com.nadson.api_produtos.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoriaRequest {

    @NotBlank(message = "O campo nome é obrigatório!")
    private String nome;

    private String descricao;

    public CategoriaRequest(){}

    public CategoriaRequest(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome(){
        return nome;
    }
    public String getDescricao(){
        return descricao;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }


}
