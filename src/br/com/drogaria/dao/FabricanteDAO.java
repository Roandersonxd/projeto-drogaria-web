package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.HibernateUtil;

public class FabricanteDAO {

	// Metedo para salvar um fabricante
	public void salvar(Fabricante fabricante) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(fabricante);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}
    
	//Consulta fabricantes
	
	@SuppressWarnings("unchecked")
	public List<Fabricante> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		//	Query consulta = null;
		List<Fabricante> fabricantes = null;
		try {
			Query consulta = sessao.getNamedQuery("Fabricante.listar");
			fabricantes = consulta.list();
		} catch (RuntimeException ex) {
              throw  ex;
 		}finally{
 			sessao.close();
 		}
		return fabricantes;
	}
	
	
	//Buscar pelo codigo
	public Fabricante buscarPorCodigo(Long codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Fabricante fabricante  = null;
		try {
			Query consulta = sessao.getNamedQuery("Fabricante.buscarPorCodigo");
			consulta.setLong("codigo",codigo);
			fabricante = (Fabricante) consulta.uniqueResult();
			
		} catch (RuntimeException ex) {
              throw  ex;
 		}finally{
 			sessao.close();
 		}
		return fabricante;
	}
	
	
	// Metedo para excluir um fabricante
		public void excluir(Fabricante fabricante) {
			Session sessao = HibernateUtil.getSessionFactory().openSession();
			Transaction transacao = null;
			try {
				transacao = sessao.beginTransaction();
				sessao.delete(fabricante);
				transacao.commit();
			} catch (RuntimeException ex) {
				if (transacao != null) {
					transacao.rollback();
				}
				throw ex;
			} finally {
				sessao.close();
			}
		}
		
		public void excluir(Long codigo) {
			Session sessao = HibernateUtil.getSessionFactory().openSession();
			Transaction transacao = null;
			try {
				transacao = sessao.beginTransaction();
				Fabricante fabricante = buscarPorCodigo(codigo);
				sessao.delete(fabricante);
				transacao.commit();
			} catch (RuntimeException ex) {
				if (transacao != null) {
					transacao.rollback();
				}
				throw ex;
			} finally {
				sessao.close();
			}
		}
		
		//Editar fabricante
		public void editar(Fabricante fabricante) {
			Session sessao = HibernateUtil.getSessionFactory().openSession();
			Transaction transacao = null;
			try {
				transacao = sessao.beginTransaction();
                sessao.update(fabricante);
				transacao.commit();
			} catch (RuntimeException ex) {
				if (transacao != null) {
					transacao.rollback();
				}
				throw ex;
			} finally {
				sessao.close();
			}
		}

}
