package br.com.SolutionProd.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

public class CustoTest {
	

@Test
	@Ignore
	public void salvar() throws ParseException{
		
		Custo c1  = new Custo();
		Produto p1 = new Produto();
	;
		
	
		ProdutoDAO pdao = new ProdutoDAO();
		CustoDAO cdao = new CustoDAO();
		p1 = pdao.buscarPorCodigo(1);	
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
			
			c1.setData(format.parse("22/11/2018"));		
		
		cdao.salvar(c1);	
		
		
	}
	
	@Test
	@Ignore
	public void listar(){
		CustoDAO cdao = new CustoDAO();
		List<Custo> custos = cdao.listar();
		
		for(Custo custo : custos){
			System.out.println(custo);
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		CustoDAO cdao = new CustoDAO();
		Custo c1  = cdao.buscarPorCodigo(1);
		
		
		
			System.out.println(c1);
			
	}
	
	@Test
	@Ignore
	public void excluir(){
		CustoDAO cdao = new CustoDAO();
		Custo c1  = cdao.buscarPorCodigo(1);
		
		cdao.excluir(c1);
		
		
			
	}
	
	
	
	@Test
	@Ignore
	public void editar(){
		
		CustoDAO cdao = new CustoDAO();
		Custo c1  = cdao.buscarPorCodigo(1);
		
		
		cdao.editar(c1);
		
			
	}
	
	

}
