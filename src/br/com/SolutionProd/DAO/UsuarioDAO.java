package br.com.SolutionProd.DAO;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.SolutionProd.domain.Fornecedor;
import br.com.SolutionProd.domain.Usuario;
import br.com.SolutionProd.util.HibernateUtil;

public class UsuarioDAO {
	
	private Class<Usuario> classe;
	public Usuario autenticar(String nome, String senha) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.createAlias("funcionario", "f");
			consulta.add(Restrictions.eq("f.nome", nome));
			SimpleHash hash = new SimpleHash("md5" , senha);
			consulta.add(Restrictions.eqOrIsNull("senha", hash.toHex()));
			Usuario resultado = (Usuario) consulta.uniqueResult();
			
			
			return resultado;
			
		} catch (RuntimeException erro) {
		throw erro;
		}finally {
			sessao.close();
		}
		
		
		
	}
	
	public void salvar(Usuario usuario) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			sessao.save(usuario);
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
	public List<Usuario> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<Usuario> usuarios = null;

		try {
			
			Query consulta = sessao.getNamedQuery("Usuario.listar");
			usuarios = consulta.list();
			
		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}
		return usuarios;
		
	}

	public Usuario buscarPorCodigo(Integer codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Usuario usuario = null;

		try {

			Query consulta = sessao.getNamedQuery("Usuario.buscarPorCodigo");
			consulta.setInteger("id", codigo);
			usuario = (Usuario) consulta.uniqueResult();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}

		return usuario;
	}

	public void excluir(Usuario usuario) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			sessao.delete(usuario);
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

	public void editar(Usuario usuario) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação

			sessao.update(usuario);
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

	public Usuario merge(Usuario usuario) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			@SuppressWarnings("unchecked")
			Usuario retorno = (Usuario) sessao.merge(usuario);
			transacao.commit();
			return retorno;
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	
	
	
}