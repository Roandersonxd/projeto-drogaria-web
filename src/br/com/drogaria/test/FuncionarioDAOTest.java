package br.com.drogaria.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;

public class FuncionarioDAOTest {
	
	@Test
	@Ignore
	public void salvar(){
		Funcionario funcionario = new Funcionario();
		funcionario.setCpf("222.222.222-22");
		funcionario.setFuncao("PROGRAMADOR");
		funcionario.setNome("ROANDERSON");
		funcionario.setSenha("q1w2e3r4");
		
		FuncionarioDAO dao = new FuncionarioDAO();
		dao.salvar(funcionario);
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		FuncionarioDAO dao =  new FuncionarioDAO();
	    Funcionario funcionario = dao.buscarPorCodigo(1L);
	    System.out.println(funcionario);
	}
	
	@Test
	@Ignore
	public void excluir(){
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario funcionario = dao.buscarPorCodigo(3L);
		dao.excluir(funcionario);
	}
	
	@Test
	@Ignore
	public void editar(){
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario funcionario = dao.buscarPorCodigo(3L);
		
		funcionario.setCpf("333.333.333-33");
		funcionario.setFuncao("ADMINSTRADOR");
		funcionario.setNome("JOAO DA  SILVA");
		funcionario.setSenha("R7r12345");
       
		dao.editar(funcionario);
	}
	
	@Test
	@Ignore
	public void autenticar(){
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		
		Funcionario funcionario = funcionarioDAO.autenticar("652.578.357-70","19932008");
		
		Assert.assertNotNull(funcionario);
	}

}
