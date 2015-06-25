package br.com.drogaria.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

public class ProdutoDAOTeste {

	@Test
	@Ignore
	public void salvar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscarPorCodigo(6L);// Trazendo o
																	// fabricante
																	// para
																	// efetuar o
																	// cadastro
																	// de
																	// produto

		Produto produto = new Produto();
		produto.setDescricao("REMÉDIO X");
		produto.setPreco(new BigDecimal(23.00D));
		produto.setQuantidade(30);
		produto.setFabricante(fabricante);

		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorCodigo(3L);
		System.out.println(produto);
	}
	
	@Test
	@Ignore
	public void Listar(){
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> produtos = produtoDAO.listar();
		System.out.println(produtos);
	}
	
	@Test
	@Ignore
	public void excluir(){
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto =  produtoDAO.buscarPorCodigo(2L);
		produtoDAO.excluir(produto);
	}
	
	@Test
	
	public void editar(){
	    ProdutoDAO produtoDAO = new ProdutoDAO();
	    Produto produto = produtoDAO.buscarPorCodigo(1L);
	    produto.setDescricao("testeeeeee");
	    produto.setPreco(new BigDecimal(6.37D));
	    produto.setQuantidade(50);
	    
	    
	    FabricanteDAO fabricanteDAO = 	new FabricanteDAO();
	    Fabricante fabricante= fabricanteDAO.buscarPorCodigo(3L);
	    produto.setFabricante(fabricante);
	    
	    produtoDAO.editar(produto);
	}

}
