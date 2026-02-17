package com.nadson.api_produtos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;


@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private double preco;
    private int estoque;

    @Column(name="criado_em", updatable=false)
    private LocalDateTime criadoEm;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Produto(){}

    public Produto(String nome, double preco, int estoque, LocalDateTime criadoEm){
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.criadoEm = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate(){
        this.criadoEm = LocalDateTime.now();
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
    public LocalDateTime getCriadoEm(){return criadoEm;}
    public Categoria getCategoria(){return categoria;}

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
    public void setCategoria(Categoria categoria){this.categoria = categoria;}
    public void setCriadoEm(LocalDateTime criadoEm){this.criadoEm = criadoEm;}
}
