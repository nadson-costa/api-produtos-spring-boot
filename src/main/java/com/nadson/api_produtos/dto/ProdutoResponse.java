package com.nadson.api_produtos.dto;

import java.time.LocalDateTime;

public class ProdutoResponse {
    private Long id;
    private String nome;
    private double preco;
    private int estoque;
    private LocalDateTime criadoEm;

    private Long categoriaId;
    private String categoriaNome;

    public ProdutoResponse(){}

    public ProdutoResponse(String nome, double preco, int estoque){
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public Long getId(){
        return id;
    }
    public String getNome(){
        return nome;
    }
    public double getPreco(){
        return preco;
    }
    public int getEstoque(){
        return estoque;
    }
    public LocalDateTime getCriadoEm(){
        return criadoEm;
    }
    public Long getCategoriaId(){
        return categoriaId;
    }
    public String getCategoriaNome(){
        return categoriaNome;
    }


    public void setId(Long id){
        this.id = id;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setPreco(double preco){
        this.preco = preco;
    }
    public void setEstoque(int estoque){
        this.estoque = estoque;
    }
    public void setCriadoEm(LocalDateTime criadoEm){
        this.criadoEm = criadoEm;
    }
    public void setCategoriaId(Long categoriaId){
        this.categoriaId = categoriaId;
    }
    public void setCategoriaNome(String categoriaNome){
        this.categoriaNome = categoriaNome;
    }

}
