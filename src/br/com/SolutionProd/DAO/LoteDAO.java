package br.com.SolutionProd.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.SolutionProd.domain.Lote;
import br.com.SolutionProd.util.HibernateUtil;

public class LoteDAO {
	

	public void salvar(Lote lote) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			sessao.save(lote);
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
	public List<Lote> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<Lote> lotes = null;

		try {

			Query consulta = sessao.getNamedQuery("Lote.listar");
			lotes = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}

		return lotes;
	}

	public Lote buscarPorCodigo(int codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Lote lote = null;

		try {

			Query consulta = sessao.getNamedQuery("Lote.buscarPorCodigo");
			consulta.setInteger("id", codigo);
			lote = (Lote) consulta.uniqueResult();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}

		return lote;
	}
	
	public List<Lote> buscarPorFuncionario(int codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<Lote> lotes = null;

		try {

			Query consulta = sessao.getNamedQuery("Lote.buscarPorFuncionario");
			consulta.setInteger("id", codigo);
			lotes = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}

		return lotes;
	}
	

	public void excluir(Lote lote) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			sessao.delete(lote);
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

	public void editar(Lote lote) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação

			sessao.update(lote);
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
