package br.com.drogaria.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;

public class FabricanteDAOTest {
	
	@Test
	@Ignore
	public  void salvar(){
		Fabricante	 f1 = new Fabricante();
		f1.setDescricao("DESCRICAOA");
		Fabricante	 f2 = new Fabricante();
		f2.setDescricao("DESCRICAO2");
		
	 FabricanteDAO dao = new  FabricanteDAO();
	 dao.salvar(f1);
	 dao.salvar(f2);
		
	}
	@Test
	@Ignore
	public void listar(){
		FabricanteDAO dao = new FabricanteDAO();
		List<Fabricante>fabricantes = dao.listar();
		
      System.out.println(fabricantes);
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		
		FabricanteDAO dao = new FabricanteDAO();
		Fabricante f1 = dao.buscarPorCodigo(1L);
		System.out.println(f1);
	}
	
	@Test
	@Ignore
	public void excluir(){
		FabricanteDAO dao = new FabricanteDAO();
		Fabricante fabricante = dao.buscarPorCodigo(3L);
		dao.excluir(fabricante);
	}
	
	@Test
	@Ignore
	public void excluirPorCodigo(){
		FabricanteDAO dao = new  FabricanteDAO();
		dao.excluir(2L);
	}
	
	@Test
	@Ignore
	public void editar(){
		FabricanteDAO dao = new FabricanteDAO();
		Fabricante fabricante = dao.buscarPorCodigo(3L);
		fabricante.setDescricao("roandersonTest");
		dao.editar(fabricante);
	}

}
