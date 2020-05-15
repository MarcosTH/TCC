package br.com.SolutionProd.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.SolutionProd.domain.Fita;
import br.com.SolutionProd.domain.MaioresPedidos;
import br.com.SolutionProd.util.HibernateUtil;

public class MaioresPedidosDAO {
	
	
	@SuppressWarnings("unchecked")
	public List<MaioresPedidos> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<MaioresPedidos> maiores = null;

		try {

			Query consulta = sessao.getNamedQuery("MaioresPedidos.listar");
			maiores = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}

		return maiores;
	}

}
