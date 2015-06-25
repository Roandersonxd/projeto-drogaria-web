package br.com.drogaria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
@Path("/fab")
public class FabricanteBean {

	Fabricante fabricanteCadastro;
	private List<Fabricante> listaFabricantes;
	private List<Fabricante> listaFabricantesFiltrados;
	private String acao;
	private Long codigo;
	
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

	public Fabricante getFabricanteCadastro() {
		return fabricanteCadastro;
	}

	public void setFabricanteCadastro(Fabricante fabricanteCadastro) {
		this.fabricanteCadastro = fabricanteCadastro;
	}

	public void salvar() {

		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.salvar(fabricanteCadastro);

			fabricanteCadastro = new Fabricante();

			FacesUtil.adicionarMsgInfo("Fabricante salvo com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar incluir um fabricante:"
					+ ex.getMessage());
		}
	}
	@GET
	@Produces("application/json")
	public List<Fabricante> getListaFabricantes() {
		return listaFabricantes;
	}

	public void setListaFabricantes(List<Fabricante> listaFabricantes) {
		this.listaFabricantes = listaFabricantes;
	}

	public List<Fabricante> getListaFabricantesFiltrados() {
		return listaFabricantesFiltrados;
	}

	public void setListaFabricantesFiltrados(
			List<Fabricante> listaFabricantesFiltrados) {
		this.listaFabricantesFiltrados = listaFabricantesFiltrados;
	}

	public void novo() {
		fabricanteCadastro = new Fabricante();
	}

	public void carregarPesquisa() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			listaFabricantes = fabricanteDAO.listar();

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar listar os fabricantes: "
					+ ex.getMessage());
		}
	}

	public void carregarCadastro() {
		try {
			
			if (codigo!= null) {
				FabricanteDAO fabricanteDAO = new FabricanteDAO();
				fabricanteCadastro = fabricanteDAO.buscarPorCodigo(codigo);
			}else{
				fabricanteCadastro = new Fabricante();
			}
		} catch (RuntimeException ex) {
			FacesUtil
					.adicionarMsgErro("Erro ao tentar obter os dados do fabricantes: "
							+ ex.getMessage());
		}
	}
	
   public void excluir(){
	   try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.excluir(fabricanteCadastro);
			FacesUtil.adicionarMsgInfo("Fabricante removido com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar remover o fabricante:"
					+ ex.getMessage());
		}

   }
   
   public void editar(){
	   try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.editar(fabricanteCadastro);


			FacesUtil.adicionarMsgInfo("Fabricante editado  com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar o fabricante:"
					+ ex.getMessage());
		}

   }
}
