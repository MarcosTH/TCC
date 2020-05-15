package br.com.SolutionProd.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;


import br.com.SolutionProd.DAO.FornecedorDAO;
import br.com.SolutionProd.DAO.MaterialDAO;

import br.com.SolutionProd.domain.Fornecedor;
import br.com.SolutionProd.domain.Material;

public class MaterialTest {



@Test
@Ignore
	public void salvar(){
		
		Material m1 = new Material();
		FornecedorDAO daof = new FornecedorDAO();
		
		Fornecedor f1 = daof.buscarPorCodigo(1);
		
		m1.setNome("regulador nylon 20");
		m1.setEstoqueMinimo(1000);
		m1.setPreco(new BigDecimal(20));
		m1.setQuantidade(3000);
		m1.setCodigo("36021-15");
		m1.setFornecedor(f1);
		
		
	
		
		
		MaterialDAO dao = new MaterialDAO();
		
		
		dao.salvar(m1);
		
	}
	
	@Test
	@Ignore
	public void listar(){
		MaterialDAO dao = new MaterialDAO();
		List<Material> materiais = dao.listar();
		
		for(Material material : materiais){
			System.out.println(material);
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		MaterialDAO dao = new MaterialDAO();
		Material f1 = dao.buscarPorID(1);
		
		
		
			System.out.println(f1);
			
	}
	
	@Test
	@Ignore
	public void excluir(){
		
		
		MaterialDAO dao = new MaterialDAO();
		Material f1 = dao.buscarPorID(1);
		
		
		dao.excluir(f1);
		
		
			
	}
	
	
	
	@Test
	@Ignore
	public void editar(){
		
		MaterialDAO dao = new MaterialDAO();
		Material f1 = dao.buscarPorID(1);
		
		
		f1.setNome("regulador 20");
		
		dao.editar(f1);
		
			
	}
	
	@Test
	
	public void listarFaltas(){
		MaterialDAO dao = new MaterialDAO();
		List<Material> materiais = dao.listarFaltas();
		
		for(Material material : materiais){
			System.out.println(material);
		}
	}
	
	
	
}
