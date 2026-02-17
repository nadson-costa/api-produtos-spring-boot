package com.nadson.api_produtos.mapper;

import com.nadson.api_produtos.dto.ProdutoRequest;
import com.nadson.api_produtos.dto.ProdutoResponse;
import com.nadson.api_produtos.exception.ResourceNotFoundException;
import com.nadson.api_produtos.model.Categoria;
import com.nadson.api_produtos.model.Produto;
import com.nadson.api_produtos.service.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoMapperTest {

    @Mock
    private CategoriaService categoriaService;

    @InjectMocks
    private ProdutoMapper mapper;

    private Categoria categoria;
    private Produto produto;
    private ProdutoRequest request;

    @BeforeEach
    void setup(){
        categoria = new Categoria("Eletrônicos", "Produtos eletrônicos");

        produto = new Produto();
        produto.setNome("Mouse");
        produto.setPreco(150.0);
        produto.setEstoque(50);
        produto.setCategoria(categoria);

        request = new ProdutoRequest("Mouse", 150.0, 50);
        request.setCategoriaId(1L);
    }

    @Test
    void converterRequestParaEntity(){
        when(categoriaService.buscarPorId(1L)).thenReturn(Optional.of(categoria));

        Produto resultado = mapper.toEntity(request);

        assertNotNull(resultado);
        assertEquals("Mouse", resultado.getNome());
        assertEquals(150.0, resultado.getPreco());
        assertEquals(50, resultado.getEstoque());
        assertEquals(categoria, resultado.getCategoria());

        verify(categoriaService, times(1)).buscarPorId(1L);
    }

    @Test
    void converterEntityParaResponse(){
        ProdutoResponse response = mapper.toResponse(produto);

        assertNotNull(response);
        assertEquals("Mouse", response.getNome());
        assertEquals(150.0, response.getPreco());
        assertEquals(50, response.getEstoque());

        assertEquals("Eletrônicos", response.getCategoriaNome());
    }

    @Test
    void lancarExcecaoQuandoCategoriaNaoExiste(){
        when(categoriaService.buscarPorId(999L))
                .thenReturn(Optional.empty());

        request.setCategoriaId(999L);

        assertThrows(ResourceNotFoundException.class, () -> {
            mapper.toEntity(request);
        });

        verify(categoriaService, times(1)).buscarPorId(999L);
    }

    @Test
    void retornarResponseComCategoriaNull(){
        produto.setCategoria(null);

        ProdutoResponse response = mapper.toResponse(produto);

        assertNotNull(response);
        assertEquals("Mouse", response.getNome());
        assertNull(response.getCategoriaId());
        assertNull(response.getCategoriaNome());
    }

    @Test
    void preencherTodosCamposDoResponse(){
        produto.setId(1L);
        produto.setCriadoEm(LocalDateTime.now());

        ProdutoResponse response = mapper.toResponse(produto);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Mouse", response.getNome());
        assertEquals(150.0, response.getPreco());
        assertEquals(50, response.getEstoque());
        assertNotNull(response.getCriadoEm());
        assertEquals("Eletrônicos", response.getCategoriaNome());
    }


}
