package br.com.SolutionProd.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.SolutionProd.DAO.CustoDAO;
import br.com.SolutionProd.DAO.FornecedorDAO;
import br.com.SolutionProd.DAO.FuncionarioDAO;
import br.com.SolutionProd.DAO.LoteDAO;
import br.com.SolutionProd.DAO.NFDAO;
import br.com.SolutionProd.DAO.ProdutoDAO;
import br.com.SolutionProd.domain.Custo;
import br.com.SolutionProd.domain.Fornecedor;
import br.com.SolutionProd.domain.Funcionario;
import br.com.SolutionProd.domain.Lote;
import br.com.SolutionProd.domain.NF;
import br.com.SolutionProd.domain.Produto;

public class NFTest {
	
	@Test	
	
	public void salvar() throws ParseException{
		
		
		NF nf = new NF();
		NFDAO nfdao = new NFDAO();
		Fornecedor f = new Fornecedor();
		FornecedorDAO fdao = new FornecedorDAO();
		f = fdao.buscarPorCodigo(5);
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		nf.setData(format.parse("01/08/2018"));
		
		nf.setFornecedor(f);
		nfdao.salvar(nf);
		
		
		
	}
	
	@Test
	@Ignore
	public void listar(){
		
		NFDAO nfdao = new NFDAO();
		List<NF> nfs = nfdao.listar();
		
		for(NF nf : nfs){
			System.out.println(nf);
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		
		
		NFDAO nfdao = new NFDAO();
		NF nf = nfdao.buscarPorCodigo(1);
		
			System.out.println(nf);
			
	}
	
	@Test
	@Ignore
	public void excluir(){
		NFDAO nfdao = new NFDAO();
		NF nf = nfdao.buscarPorCodigo(1);
		
		nfdao.excluir(nf);
		
		
			
	}
	
	
	
	@Test
	@Ignore
	public void editar() throws ParseException{
		
		NFDAO nfdao = new NFDAO();
		NF nf = nfdao.buscarPorCodigo(1);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		nf.setData(format.parse("22/11/2020"));
		
		nfdao.editar(nf);
		
			
	}

}
