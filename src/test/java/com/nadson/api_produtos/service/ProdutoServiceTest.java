package com.nadson.api_produtos.service;

import com.nadson.api_produtos.model.Produto;
import com.nadson.api_produtos.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repository;

    @InjectMocks
    private ProdutoService service;

    private Produto produto;

    @BeforeEach
    void setUp(){
        produto = new Produto();
        produto.setId(1L);
        produto.setNome("Mouse");
        produto.setPreco(150.0);
        produto.setEstoque(50);
    }

    @Test
    void listarTodosProdutos(){
        List<Produto> produtos = Arrays.asList(produto);
        when(repository.findAll()).thenReturn(produtos);

        List<Produto> resultado = service.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Mouse", resultado.get(0).getNome());

        verify(repository, times(1)).findAll();
    }

    @Test
    void buscarProdutoPorId(){
        OngoingStubbing<Optional<Produto>> optionalOngoingStubbing = when(repository.findById(1L)).thenReturn(Optional.of(produto));

        Optional<Produto> resultado = service.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Mouse", resultado.get().getNome());

        verify(repository, times(1)).findById(1L);
    }

    @Test
    void retornarVazioQuandoProdutoNaoExiste(){
        when(repository.findById(999L)).thenReturn(Optional.empty());

        Optional<Produto> resultado = service.buscarPorId(999L);

        assertFalse(resultado.isPresent());

        verify(repository, times(1)).findById(999L);
    }

    @Test
    void salvarProduto(){
        when(repository.save(produto)).thenReturn(produto);

        Produto salvo = service.salvar(produto);

        assertNotNull(salvo);
        assertEquals("Mouse", salvo.getNome());

        verify(repository, times(1)).save(produto);
    }

    @Test
    void deletarProduto(){
        doNothing().when(repository).deleteById(1L);

        service.deletar(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}