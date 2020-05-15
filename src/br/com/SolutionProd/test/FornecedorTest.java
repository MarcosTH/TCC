package br.com.SolutionProd.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.SolutionProd.DAO.FornecedorDAO;
import br.com.SolutionProd.domain.Fornecedor;



public class FornecedorTest {
	
@Test
@Ignore
	public void salvar(){
		Fornecedor f4 = new Fornecedor();
		
		
		f4.setNome("eu");
		f4.setEmail("fib56e@qi");
		
		
		
		FornecedorDAO dao = new FornecedorDAO();
		
		
		dao.salvar(f4);
		
	}
	
	@Test
	@Ignore
	public void listar(){
		FornecedorDAO dao = new FornecedorDAO();
		List<Fornecedor> fornecedores = dao.listar();
		
		for(Fornecedor fornecedor : fornecedores){
			System.out.println(fornecedor);
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		FornecedorDAO dao = new FornecedorDAO();
		Fornecedor f1 = dao.buscarPorCodigo(1);
		
		
		
			System.out.println(f1);
			
	}
	
	@Test
	@Ignore
	public void excluir(){
		FornecedorDAO dao = new FornecedorDAO();
		
		Fornecedor fornecedor = dao.buscarPorCodigo(1);
		
		
		dao.excluir(fornecedor);
		
		
			
	}
	
	
	
	@Test
	@Ignore
	public void editar(){
		
		FornecedorDAO dao = new FornecedorDAO();
		
		Fornecedor fornecedor = dao.buscarPorCodigo(1);
		fornecedor.setNome("Paula Campos");
		
		dao.editar(fornecedor);
		
			
	}
	

}
