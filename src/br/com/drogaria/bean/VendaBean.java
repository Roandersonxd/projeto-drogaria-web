package br.com.drogaria.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;










import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.dao.ItemDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.dao.VendaDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.domain.Item;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.domain.Venda;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VendaBean {
	private List<Produto> listaProdutos;
	private List<Produto> listaProdutosFiltrados;
	private Venda vendaCadastro;
	
	public Venda getVendaCadastro() {
		if(vendaCadastro==null){
			vendaCadastro= new Venda();
			vendaCadastro.setValor(new BigDecimal("0.00"));
		}
		return vendaCadastro;
	}
	
	public void setVendaCadastro(Venda vendaCadastro) {
		this.vendaCadastro = vendaCadastro;
	}
	
	private List<Item>  listaItens;
	
	public List<Item> getListaItens() {
		if(listaItens==null){
			listaItens = new ArrayList<>();
		}
		return listaItens;
	}
	public void setListaItens(List<Item> listaItens) {
		this.listaItens = listaItens;
	}
	
	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}
	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	public List<Produto> getListaProdutosFiltrados() {
		return listaProdutosFiltrados;
	}
	public void setListaProdutosFiltrados(List<Produto> listaProdutosFiltrados) {
		this.listaProdutosFiltrados = listaProdutosFiltrados;
	}
	
	
	//Carrega os produtos
	
	public void carregarProdutos() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			listaProdutos = produtoDAO.listar();

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar listar os produtos: "
					+ ex.getMessage());
		}
	}
	
	
	public void adcionar(Produto produto){
		int posicaoEncontrada=-1;
		
		//Verificando se um produto foi adcionado mais de uma vez
		for(int pos= 0;pos<listaItens.size() && posicaoEncontrada < 0 ;pos++){
			Item itemTemp = listaItens.get(pos);
  			if(itemTemp.getProduto().equals(produto)){
	             posicaoEncontrada=pos;			
			}
		}
		
	//Transformar produto em item
		Item item = new Item();
		item.setProduto(produto);
		
		if(posicaoEncontrada < 0){
			item.setQuantidade(1);
			item.setValor(produto.getPreco());
			listaItens.add(item);	
		}else{
			Item itemTemp = listaItens.get(posicaoEncontrada);
			item.setQuantidade(itemTemp.getQuantidade()+1);
			item.setValor(produto.getPreco().multiply(new BigDecimal(item.getQuantidade())));
			listaItens.set(posicaoEncontrada,item);
		}
		
		vendaCadastro.setValor(vendaCadastro.getValor().add(produto.getPreco()));
	}
	
	public void remover(Item item){
		
       int posicaoEncontrada=-1;
		
		for(int pos= 0;pos<listaItens.size() && posicaoEncontrada < 0 ;pos++){
			Item itemTemp = listaItens.get(pos);
  			if(itemTemp.getProduto().equals(item.getProduto())){
	             posicaoEncontrada=pos;			
			}
		}
		if(posicaoEncontrada > -1){
			listaItens.remove(posicaoEncontrada);
			vendaCadastro.setValor(vendaCadastro.getValor().subtract(item.getValor()));
		}
	}
	
	public void carregarDadosVenda(){
		vendaCadastro.setHorario(new Date());
		
	   FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	   Funcionario funcionario = funcionarioDAO.buscarPorCodigo(3L);
	   vendaCadastro.setFuncionario(funcionario);
	}
	
	public void salvar(){
	  try{
		 VendaDAO vendaDAO = new VendaDAO();
		 Long codigoVenda = vendaDAO.salvar(vendaCadastro);
		 Venda vendaFK = vendaDAO.buscarPorCodigo(codigoVenda);
		 
		 for(Item item:listaItens){
			 item.setVenda(vendaFK);
			 ItemDAO itemDAO = new ItemDAO();
			 itemDAO.salvar(item);
		 }
		  
		vendaCadastro = new Venda();
		vendaCadastro.setValor(new BigDecimal("0.00"));
		
		listaItens = new ArrayList<Item>();
		
		  FacesUtil.adicionarMsgInfo("Venda salva com sucesso");
	  }catch(RuntimeException ex){
		  FacesUtil.adicionarMsgErro("Erro ao tentar salvar a venda :"+ex.getMessage());
	  }
	}
   
}
