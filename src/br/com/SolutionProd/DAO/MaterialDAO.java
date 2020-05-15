package br.com.SolutionProd.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.SolutionProd.domain.Funcionario;
import br.com.SolutionProd.domain.Material;
import br.com.SolutionProd.util.HibernateUtil;

public class MaterialDAO {
	

	public void salvar(Material material) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			sessao.save(material);
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
	public void merge(Material material) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			sessao.merge(material);
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
	public List<Material> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<Material> materiais = null;

		try {

			Query consulta = sessao.getNamedQuery("Material.listar");
			materiais = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}

		return materiais;
	}

	
	@SuppressWarnings("unchecked")
	public List<Material> listarMaiores() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<Material> materiais = null;

		try {

			Query consulta = sessao.getNamedQuery("Material.listarMaiores");
			materiais = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}

		return materiais;
	}


	
	@SuppressWarnings("unchecked")
	public List<Material> listarFaltas() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<Material> materiais = null;

		try {

			Query consulta = sessao.getNamedQuery("Material.listarFaltas");
			materiais = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}

		return materiais;
	}

	
	
	
	public void excluir(Material material) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			sessao.delete(material);
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

	public void editar(Material material) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação

			sessao.update(material);
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
	
	
	public Material buscarPorID(int codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Material material = null;

		try {

			Query consulta = sessao.getNamedQuery("Material.buscarPorId");
			consulta.setInteger("id", codigo);
			material = (Material) consulta.uniqueResult();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}

		return material;
	}
	
	
	
	


}
