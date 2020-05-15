package br.com.SolutionProd.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.SolutionProd.domain.Fita;
import br.com.SolutionProd.util.HibernateUtil;

public class FitaDAO {
	

	public void salvar(Fita fita) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			sessao.save(fita);
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
	public List<Fita> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<Fita> fitas = null;

		try {

			Query consulta = sessao.getNamedQuery("Fita.listar");
			fitas = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}

		return fitas;
	}

	public Fita buscarPorCodigo(Integer codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Fita fita = null;

		try {

			Query consulta = sessao.getNamedQuery("Fita.buscarPorCodigo");
			consulta.setInteger("id", codigo);
			fita = (Fita) consulta.uniqueResult();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}

		return fita;
	}

	public void excluir(Fita fita) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			sessao.delete(fita);
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

	public void editar(Fita fita) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação

			sessao.update(fita);
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



