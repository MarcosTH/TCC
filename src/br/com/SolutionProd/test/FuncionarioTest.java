package br.com.SolutionProd.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;


import br.com.SolutionProd.DAO.FornecedorDAO;
import br.com.SolutionProd.DAO.FuncionarioDAO;

import br.com.SolutionProd.domain.Fornecedor;
import br.com.SolutionProd.domain.Funcionario;

public class FuncionarioTest {
	

@Test

	public void salvar(){
		Funcionario fun = new Funcionario();
		FuncionarioDAO dao = new FuncionarioDAO();		
		fun.setNome("Marcos");
		fun.setFuncao("administrador");	
		
		dao.salvar(fun);
	}
	
	@Test
@Ignore
	public void listar(){
		FuncionarioDAO dao = new FuncionarioDAO();
		List<Funcionario> funcionarios =dao.listar();
		
		for(Funcionario forn : funcionarios){
			System.out.println(forn);
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario fun = dao.buscarPorCodigo(3);
		
		
		
			System.out.println(fun);
			
	}
	
	@Test
	@Ignore
	public void excluir(){
		FuncionarioDAO dao = new FuncionarioDAO();
		
		Funcionario fun = dao.buscarPorCodigo(1);
		
		
		dao.excluir(fun);
		
		
			
	}
	
	
	
	@Test
	@Ignore
	public void editar(){
		

		
		
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario fun = dao.buscarPorCodigo(2);
		
		fun.setNome("wdwdwd");
		

		
		dao.editar(fun);	
			
	}
	
	
	

}
