package br.com.SolutionProd.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.SolutionProd.domain.Custo;
import br.com.SolutionProd.domain.ItemNF;
import br.com.SolutionProd.util.HibernateUtil;

public class ItemNFDAO {
	
	public void salvar(ItemNF item) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			sessao.save(item);
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
	public List<ItemNF> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<ItemNF> itens = null;

		try {

			Query consulta = sessao.getNamedQuery("ItemNF.listar");
			itens = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}

		return itens;
	}

	public ItemNF buscarPorCodigo(int codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		ItemNF item = null;

		try {

			Query consulta = sessao.getNamedQuery("ItemNF.buscarPorCodigo");
			consulta.setInteger("id", codigo);
			item = (ItemNF) consulta.uniqueResult();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}

		return item;
	}

	public void excluir(ItemNF item) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			sessao.delete(item);
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

	public void editar(ItemNF item) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação

			sessao.update(item);
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
