package br.com.SolutionProd.main;

import java.math.BigDecimal;


import br.com.SolutionProd.DAO.FornecedorDAO;

import br.com.SolutionProd.domain.Fornecedor;
import br.com.SolutionProd.util.HibernateUtil;

public class GerarTabela {

	public static void main(String[] args) {
		
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();
			
		
		
	}

}
