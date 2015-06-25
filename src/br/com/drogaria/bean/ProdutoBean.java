package br.com.drogaria.bean;

import java.util.List;





import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ProdutoBean {

	Produto produtoCadastro;
	private List<Produto> listaProdutos;
	private List<Produto> listaProdutosFiltrados;
	private Produto produtoSelecionado;
	private List<Fabricante>listaFabricantes;
	private String acao;
	private Long codigo;
	
	
	private PieChartModel pieModel;
	
	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}
	public PieChartModel getPieModel() {
		return pieModel;
	}
	
	
	public List<Fabricante> getListaFabricantes() {
		return listaFabricantes;
	}
	public void setListaFabricantes(List<Fabricante> listaFabricantes) {
		this.listaFabricantes = listaFabricantes;
	}
	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}
	
	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}	

	public Produto getProdutoCadastro() {
		return produtoCadastro;
	}

	public void setProdutoCadastro(Produto produtoCadastro) {
		this.produtoCadastro = produtoCadastro;
	}

	public void salvar() {

		try {
		    ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.salvar(produtoCadastro);

			produtoCadastro = new Produto();

			FacesUtil.adicionarMsgInfo("Produto salvo com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar incluir um produto:"
					+ ex.getMessage());
		}
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

	public void setListaProdutosFiltrados(
			List<Produto> listaProdutosFiltrados) {
		this.listaProdutosFiltrados = listaProdutosFiltrados;
	}

	public void novo() {
		produtoCadastro = new Produto();
	}

	public void carregarPesquisa() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			listaProdutos = produtoDAO.listar();
			grafico(listaProdutos);
			

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar listar os produtos: "
					+ ex.getMessage());
		}
	}
	
    public void grafico	(List<Produto> lista){
		pieModel = new PieChartModel();
		
		for(Produto pro:lista){
			pieModel.set(pro.getDescricao(),pro.getQuantidade());
		}
		pieModel.setTitle("Quantidade");
		pieModel.setLegendPosition("e");
		pieModel.setFill(true);
		pieModel.setShowDataLabels(true);
		pieModel.setDiameter(150);
		
	}

	public void carregarCadastro() {
		try {
			
			if (codigo!= null) {
				ProdutoDAO produtoDAO = new ProdutoDAO();
				produtoCadastro = produtoDAO.buscarPorCodigo(codigo);
			}else{
				produtoCadastro = new Produto();
			}
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			listaFabricantes = fabricanteDAO.listar();
		} catch (RuntimeException ex) {
			FacesUtil
					.adicionarMsgErro("Erro ao tentar obter os dados do produtos: "
							+ ex.getMessage());
		}
	}

	
   public void excluir(){
	   try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produtoCadastro);


			FacesUtil.adicionarMsgInfo("Fabricante removido com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar remover o fabricante:"
					+ ex.getMessage());
		}

   }
   
   public void editar(){
	   try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.editar(produtoCadastro);


			FacesUtil.adicionarMsgInfo("Produto editado  com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar o produto:"
					+ ex.getMessage());
		}

   }
}
