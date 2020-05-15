package br.com.SolutionProd.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.SolutionProd.domain.Custo;
import br.com.SolutionProd.domain.Fornecedor;
import br.com.SolutionProd.domain.NF;
import br.com.SolutionProd.util.HibernateUtil;

public class NFDAO {
	

	public void salvar(NF nf) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			sessao.save(nf);
			
			
			
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
	public List<NF> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<NF> nfs = null;

		try {

			Query consulta = sessao.getNamedQuery("NF.listar");
			nfs = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}

		return nfs;
	}
	
	@SuppressWarnings("unchecked")
	public List<NF> listarMaiores() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<NF> nfs = null;

		try {

			Query consulta = sessao.getNamedQuery("NF.listarMaiores");
			nfs = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}

		return nfs;
	}

	public NF buscarPorCodigo(int codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		NF nf = null;

		try {

			Query consulta = sessao.getNamedQuery("NF.buscarPorCodigo");
			consulta.setInteger("id", codigo);
			nf = (NF) consulta.uniqueResult();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}

		return nf;
	}
	
	public List<NF> buscarPorFornecedor(int fornecedorID) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<NF> nfs= null;

		try {

			Query consulta = sessao.getNamedQuery("NF.buscarPorFornecedor");
			consulta.setInteger("id", fornecedorID);
			nfs = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}

		return nfs;
	}

	public void excluir(NF nf) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			sessao.delete(nf);
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

	public void editar(NF nf) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação

			sessao.update(nf);
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
