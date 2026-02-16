package com.nadson.api_produtos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();

    public Categoria(){}

    public Categoria(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId(){
        return id;
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
