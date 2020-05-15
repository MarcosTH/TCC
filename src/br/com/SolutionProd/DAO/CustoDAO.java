package br.com.SolutionProd.DAO;

import java.sql.Connection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.ReturningWork;
import br.com.SolutionProd.domain.Custo;

import br.com.SolutionProd.util.HibernateUtil;

public class CustoDAO {
	

	public void salvar(Custo custo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;
		

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			 sessao.save(custo);
			transacao.commit(); // confirmando a transação

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback(); // desfaz a transação
			}
			throw ex;
		}
		

		finally {
			sessao.close();
		}

	
	}

	@SuppressWarnings("unchecked")
	public List<Custo> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<Custo> custos = null;

		try {

			Query consulta = sessao.getNamedQuery("Custo.listar");
			custos = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}

		return custos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Custo> listarMargem() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<Custo> custos = null;

		try {

			Query consulta = sessao.getNamedQuery("Custo.listarMargem");
			custos = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}

		return custos;
	}

	public Custo buscarPorCodigo(int codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Custo custo = null;

		try {

			Query consulta = sessao.getNamedQuery("Custo.buscarPorCodigo");
			consulta.setInteger("id", codigo);
			custo = (Custo) consulta.uniqueResult();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}

		return custo;
	}
	
	public Custo buscarPorProduto(int codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Custo custo = null;

		try {

			Query consulta = sessao.getNamedQuery("Custo.buscarPorProduto");
			consulta.setInteger("id", codigo);
			custo = (Custo) consulta.uniqueResult();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}

		return custo;
	}

	public void excluir(Custo custo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			sessao.delete(custo);
			transacao.commit(); // confirmando a transação

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback(); // desfaz a transação
			}

		}

		finally {
			sessao.close();
		}

	}

	public void editar(Custo custo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação

			sessao.update(custo);
			transacao.commit(); // confirmando a transação

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback(); // desfaz a transação
			}
		}

		finally {
			sessao.close();
		}

	}
	
	

}
