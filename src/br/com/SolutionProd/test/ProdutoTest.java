package br.com.SolutionProd.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.SolutionProd.DAO.FornecedorDAO;
import br.com.SolutionProd.DAO.ProdutoDAO;
import br.com.SolutionProd.domain.Fornecedor;
import br.com.SolutionProd.domain.Produto;

public class ProdutoTest {
	

@Test
	@Ignore
	public void salvar(){
		
		
		
		Produto p = new Produto();
		p.setNome("peitoral fixo cetim n 2");
		//p.setPreco(new BigDecimal(13));
		p.setPrecoVenda(new BigDecimal(24));	
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.salvar(p);

		
	}
	
	@Test
@Ignore
	public void listar(){
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> produtos = dao.listar();
		
		for(Produto produto : produtos){
			System.out.println(produto);
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		ProdutoDAO dao = new ProdutoDAO();
		Produto p = dao.buscarPorCodigo(1);
		
		
		
			System.out.println(p);
			
	}
	
	@Test
	@Ignore
	public void excluir(){
		ProdutoDAO dao = new ProdutoDAO();
		Produto p = dao.buscarPorCodigo(1);
		
		
		dao.excluir(p);
		
		
			
	}
	
	
	
	@Test
	@Ignore
	public void editar(){
		
		ProdutoDAO dao = new ProdutoDAO();
		Produto p = dao.buscarPorCodigo(2);
		//p.setPreco(new BigDecimal(100));
	
		dao.editar(p);
			
	}
	


}
