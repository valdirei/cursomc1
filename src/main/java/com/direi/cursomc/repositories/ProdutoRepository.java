package com.direi.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.direi.cursomc.domain.Categoria;
import com.direi.cursomc.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
	@Transactional(readOnly = true)
	//@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	//Page<Produto> searc(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias,Pageable pageRequest);
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome,List<Categoria> categorias,Pageable pageRequest);
}
