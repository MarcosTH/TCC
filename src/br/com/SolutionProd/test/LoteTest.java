package br.com.SolutionProd.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.SolutionProd.DAO.CustoDAO;

import br.com.SolutionProd.DAO.FuncionarioDAO;
import br.com.SolutionProd.DAO.ItemMaterialCustoDAO;
import br.com.SolutionProd.DAO.LoteDAO;
import br.com.SolutionProd.DAO.MaterialDAO;
import br.com.SolutionProd.DAO.ProdutoDAO;
import br.com.SolutionProd.domain.Custo;
import br.com.SolutionProd.domain.Funcionario;
import br.com.SolutionProd.domain.ItemMaterialCusto;
import br.com.SolutionProd.domain.Lote;
import br.com.SolutionProd.domain.Material;
import br.com.SolutionProd.domain.Produto;

public class LoteTest {

	@Test

	public void salvar() throws ParseException {

		Produto p1 = new Produto();
		ProdutoDAO pdao = new ProdutoDAO();
		p1 = pdao.buscarPorCodigo(4);
		Funcionario f1 = new Funcionario();
		FuncionarioDAO fdao = new FuncionarioDAO();
		f1 = fdao.buscarPorCodigo(1);

		Lote lote = new Lote();
		LoteDAO ldao = new LoteDAO();

		lote.setProduto(p1);
		lote.setFuncionario(f1);
		lote.setQuantidade(200);

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		lote.setData(format.parse("22/11/2018"));
		this.atualizarEstoque(lote.getProduto().getId());

		ldao.salvar(lote);

	}

	public void atualizarEstoque(int idLoteProduto) {

		Material material = new Material();
		MaterialDAO mdao = new MaterialDAO();

		Produto produto = new Produto();
		ProdutoDAO pdao = new ProdutoDAO();
		produto = pdao.buscarPorCodigo(idLoteProduto);
		Custo custo = new Custo();
		CustoDAO cdao = new CustoDAO();
		custo = cdao.buscarPorProduto(produto.getId());
		custo = cdao.buscarPorProduto(produto.getId());
		ItemMaterialCustoDAO itemdao = new ItemMaterialCustoDAO();
		ArrayList<ItemMaterialCusto> itens = (ArrayList<ItemMaterialCusto>) itemdao.listarPorCusto(custo.getId());

		for (ItemMaterialCusto item : itens) {
			material = item.getMaterial();
			material.setQuantidade(material.getQuantidade() - item.getQuantidade());
			mdao.editar(material);

		}

	}

	@Test
	@Ignore
	public void listar() {
		CustoDAO cdao = new CustoDAO();
		List<Custo> custos = cdao.listar();

		for (Custo custo : custos) {
			System.out.println(custo);
		}
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		CustoDAO cdao = new CustoDAO();
		Custo c1 = cdao.buscarPorCodigo(1);

		System.out.println(c1);

	}

	@Test
	@Ignore
	public void excluir() {
		CustoDAO cdao = new CustoDAO();
		Custo c1 = cdao.buscarPorCodigo(1);

		cdao.excluir(c1);

	}

	@Test
	@Ignore
	public void editar() {

		CustoDAO cdao = new CustoDAO();
		Custo c1 = cdao.buscarPorCodigo(1);

		cdao.editar(c1);

	}

}
