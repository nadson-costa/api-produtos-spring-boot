package com.nadson.api_produtos.dto;

public class CategoriaResponse {
    private Long id;
    private String nome;
    private String descricao;

    public CategoriaResponse(){}


    public Long getId(){
        return id;
    }
    public String getNome(){
        return nome;
    }
    public String getDescricao(){
        return descricao;
    }

    public void setId(Long id){
        this.id = id;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
}
