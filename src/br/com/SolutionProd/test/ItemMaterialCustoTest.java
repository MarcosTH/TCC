package br.com.SolutionProd.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.SolutionProd.DAO.CustoDAO;

import br.com.SolutionProd.DAO.ItemMaterialCustoDAO;
import br.com.SolutionProd.DAO.MaterialDAO;
import br.com.SolutionProd.DAO.ProdutoDAO;
import br.com.SolutionProd.domain.Custo;

import br.com.SolutionProd.domain.ItemMaterialCusto;
import br.com.SolutionProd.domain.Material;
import br.com.SolutionProd.domain.Produto;

public class ItemMaterialCustoTest {

	

@Test
	@Ignore
	public void salvar() throws ParseException{
		
		Custo c1  = new Custo();
		ItemMaterialCusto item1 = new ItemMaterialCusto();
		ItemMaterialCusto item2 = new ItemMaterialCusto();
		
		ItemMaterialCustoDAO itemdao = new ItemMaterialCustoDAO();
	
		
		MaterialDAO mdao = new MaterialDAO();
		
		Material m1 = new Material();
		Material m2 = new Material();
		Material m3 = new Material();
		m1 = mdao.buscarPorID(1);
		m2 = mdao.buscarPorID(2);
		
		
		CustoDAO cdao = new CustoDAO();
	
		item1.setMaterial(m1);
		item1.setCusto(cdao.buscarPorCodigo(2));
		item1.setQuantidade(1);
		
		item2.setCusto(cdao.buscarPorCodigo(2));
		item2.setMaterial(m2);
		item2.setQuantidade(5);
		
		itemdao.salvar(item1);
		itemdao.salvar(item2);
		
		
		
		
		
		
	}
	
	@Test
	@Ignore
	public void listar(){
		ItemMaterialCustoDAO itemdao = new ItemMaterialCustoDAO();
		List<ItemMaterialCusto> itens = itemdao.listar();
		
		for(ItemMaterialCusto item : itens){
			System.out.println(item);
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		ItemMaterialCustoDAO itemdao = new ItemMaterialCustoDAO();
				ItemMaterialCusto item1 = itemdao.buscarPorCodigo(1);
		
		
		
			System.out.println(item1);
			
	}
	
	@Test
	
	public void excluir(){
		ItemMaterialCustoDAO itemdao = new ItemMaterialCustoDAO();
		ItemMaterialCusto item1 = itemdao.buscarPorCodigo(1);
		
		itemdao.excluir(item1);
		
		
			
	}
	
	
	
	@Test
	@Ignore
	public void editar(){
		
		ItemMaterialCustoDAO itemdao = new ItemMaterialCustoDAO();
		ItemMaterialCusto item1 = itemdao.buscarPorCodigo(1);
		item1.setQuantidade(70);
		
		itemdao.editar(item1);
		
			
	}
	

	
}
