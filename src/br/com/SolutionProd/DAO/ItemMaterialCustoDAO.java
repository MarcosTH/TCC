package br.com.SolutionProd.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.SolutionProd.domain.ItemMaterialCusto;
import br.com.SolutionProd.util.HibernateUtil;

public class ItemMaterialCustoDAO {
	
	

	public void salvar(ItemMaterialCusto item) {
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
	public List<ItemMaterialCusto> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<ItemMaterialCusto> itens = null;

		try {

			Query consulta = sessao.getNamedQuery("ItemMaterialCusto.listar");
			itens = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}

		return itens;
	}

	
	@SuppressWarnings("unchecked")
	public List<ItemMaterialCusto> listarPorCusto(int id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<ItemMaterialCusto> itens = null;

		try {

			Query consulta = sessao.getNamedQuery("ItemMaterialCusto.listarPorCusto");
			consulta.setInteger("id", id);
			itens = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}

		return itens;
	}
	public ItemMaterialCusto buscarPorCodigo(int codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		ItemMaterialCusto item = null;

		try {

			Query consulta = sessao.getNamedQuery("ItemMaterialCusto.buscarPorCodigo");
			consulta.setInteger("id", codigo);
			item = (ItemMaterialCusto) consulta.uniqueResult();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}

		return item;
	}

	public void excluir(ItemMaterialCusto item) {
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

	public void editar(ItemMaterialCusto item) {
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
