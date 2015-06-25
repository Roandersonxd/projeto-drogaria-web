package br.com.drogaria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@SessionScoped
public class AutenticaoBean {
	private Funcionario funcionarioLogado;

	public Funcionario getFuncionarioLogado() {
		if (funcionarioLogado == null) {
			funcionarioLogado = new Funcionario();
		}
		return funcionarioLogado;
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}
  
	
	public void entrar(){
		try{
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioLogado = funcionarioDAO.autenticar(funcionarioLogado.getCpf(),funcionarioLogado.getSenha());
			if(funcionarioLogado == null){
				FacesUtil.adicionarMsgErro("CPF e/ou senha inválidos");
			}else{
				FacesUtil.adicionarMsgInfo("Funcionário autenticado com sucesso.");
			}
		
		}catch(RuntimeException ex){
			FacesUtil.adicionarMsgErro("Erro ao tentar entrar no sistema."+ex.getMessage());
		}
	}
}
