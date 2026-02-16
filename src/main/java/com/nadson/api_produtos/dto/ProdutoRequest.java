package com.nadson.api_produtos.dto;

import com.nadson.api_produtos.model.Categoria;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProdutoRequest {

    @NotBlank(message = "O campo nome é obrigatório!")
    private String nome;

    @Min(value = 0, message = "O preço deve ser positivo!")
    private double preco;

    @Min(value = 0, message = "O estoque deve ser positivo!")
    private int estoque;

    @NotNull(message = "Categoria é obrigatória!")
    private Long categoriaId;

    public ProdutoRequest(){}

    public ProdutoRequest(String nome, double preco, int estoque){
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
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
    public Long getCategoriaId(){
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId){
        this.categoriaId = categoriaId;
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
}
