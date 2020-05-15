package br.com.SolutionProd.test;

import java.text.ParseException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.SolutionProd.DAO.CustoDAO;
import br.com.SolutionProd.DAO.ItemMaterialCustoDAO;
import br.com.SolutionProd.DAO.ItemNFDAO;
import br.com.SolutionProd.DAO.MaterialDAO;
import br.com.SolutionProd.DAO.NFDAO;
import br.com.SolutionProd.domain.Custo;
import br.com.SolutionProd.domain.ItemMaterialCusto;
import br.com.SolutionProd.domain.ItemNF;
import br.com.SolutionProd.domain.Material;
import br.com.SolutionProd.domain.NF;

public class ItemNFTest {
	
	

@Test
	
	public void salvar() throws ParseException{
		
		ItemNF item = new ItemNF();
		ItemNFDAO itemdao = new ItemNFDAO();
		
		
		NFDAO nfdao = new NFDAO();
		NF nf = nfdao.buscarPorCodigo(5);
		MaterialDAO dao = new MaterialDAO();
		Material f1 = dao.buscarPorID(3);
		
		item.setNf(nf);
		item.setMaterial(f1);
		item.setQuantidade(3000);		
		itemdao.salvar(item);
	
		
		
		
		
		
	}
	
	@Test
	@Ignore
	public void listar(){
		ItemNFDAO itemdao = new ItemNFDAO();
		List<ItemNF> itens = itemdao.listar();
		
		for(ItemNF item : itens){
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
@Ignore
	public void excluir(){
		ItemNFDAO itemdao = new ItemNFDAO();
		ItemNF item = itemdao.buscarPorCodigo(1);
		
		itemdao.excluir(item);
		
		
			
	}
	
	
	
	@Test
	@Ignore
	public void editar(){
		
		
		ItemNFDAO itemdao = new ItemNFDAO();
		ItemNF item = itemdao.buscarPorCodigo(1);
		item.setQuantidade(500);
		itemdao.editar(item);
		
			
	}

}
