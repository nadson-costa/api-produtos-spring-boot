package com.nadson.api_produtos.repository;

import com.nadson.api_produtos.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
