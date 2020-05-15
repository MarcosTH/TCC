package br.com.SolutionProd.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import br.com.SolutionProd.DAO.CustoDAO;
import br.com.SolutionProd.DAO.ItemMaterialCustoDAO;
import br.com.SolutionProd.DAO.MaterialDAO;
import br.com.SolutionProd.DAO.ProdutoDAO;
import br.com.SolutionProd.domain.Custo;
import br.com.SolutionProd.domain.ItemMaterialCusto;
import br.com.SolutionProd.domain.Material;
import br.com.SolutionProd.domain.Produto;
import br.com.SolutionProd.util.JSFUtil;
import br.com.SolutionProd.util.ReportUtils;

@ManagedBean(name = "MBCusto")
@ViewScoped
public class CustoBean implements Serializable {
 
	private Material material;

	private ArrayList<ItemMaterialCusto> itens;
	private ArrayList<ItemMaterialCusto> itensFiltrados;
	private ArrayList<Material> materiais;
	private ArrayList<Material> materiaisFiltrados;
	private Custo custoCadastro;
	private Produto produto;
	private ArrayList<Custo> custos;
	private ArrayList<Custo> custosFiltrados;
	private ArrayList<Produto> produtos;
	private ArrayList<Produto> produtosFiltrados;
	private Integer codigo;

	public ArrayList<Custo> getCustos() {
		return custos;
	}

	public void setCustos(ArrayList<Custo> custos) {
		this.custos = custos;
	}

	public ArrayList<Custo> getCustosFiltrados() {
		return custosFiltrados;
	}

	public void setCustosFiltrados(ArrayList<Custo> custosFiltrados) {
		this.custosFiltrados = custosFiltrados;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public ArrayList<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public void setProdutosFiltrados(ArrayList<Produto> produtosFiltrados) {
		this.produtosFiltrados = produtosFiltrados;
	}

	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProduto() {
		if (produto == null) {
			produto = new Produto();

		}
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Custo getCustoCadastro() {
		if (custoCadastro == null) {
			custoCadastro = new Custo();
			custoCadastro.setCusto(new BigDecimal("0.00"));

		}
		return custoCadastro;
	}

	public void setCustoCadastro(Custo custoCadastro) {
		this.custoCadastro = custoCadastro;
	}

	public ArrayList<Material> getMateriais() {
		return materiais;
	}

	public void setMateriais(ArrayList<Material> matriais) {
		this.materiais = matriais;
	}

	public ArrayList<Material> getMateriaisFiltrados() {
		return materiaisFiltrados;
	}

	public void setMateriaisFiltrados(ArrayList<Material> materiaisFiltrados) {
		this.materiaisFiltrados = materiaisFiltrados;
	}

	public Material getMaterial() {
		if (material == null) {
			material = new Material();

		}

		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public ArrayList<ItemMaterialCusto> getItens() {
		if (itens == null) {
			itens = new ArrayList<>();

		}
		return itens;
	}

	public void setItens(ArrayList<ItemMaterialCusto> itens) {
		this.itens = itens;
	}

	public ArrayList<ItemMaterialCusto> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<ItemMaterialCusto> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public void carregarMateriais() {

		try {

			MaterialDAO dao = new MaterialDAO();
			materiais = (ArrayList<Material>) dao.listar();

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

	public void carregarCustos() {

		try {
			CustoDAO custodao = new CustoDAO();
			custos = (ArrayList<Custo>) custodao.listar();

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

	public void adicionarMaterial(Material mat) {

		int posicaoEncontrada = -1;
		for (int pos = 0; pos < itens.size() && posicaoEncontrada < 0; pos++) {
			ItemMaterialCusto itemTemp = itens.get(pos);

			if (itemTemp.getMaterial().getNome().equals(mat.getNome())) {
				posicaoEncontrada = pos;
			}
		}

		ItemMaterialCusto item = new ItemMaterialCusto();
		item.setMaterial(mat);

		if (posicaoEncontrada < 0) {

			item.setQuantidade(1);
			item.setValorParcial(mat.getPreco());
			itens.add(item);
		} else {

			ItemMaterialCusto itemTemp = itens.get(posicaoEncontrada);
			item.setQuantidade(itemTemp.getQuantidade() + 1);
			item.setValorParcial(mat.getPreco().multiply(new BigDecimal(item.getQuantidade())));
			itens.set(posicaoEncontrada, item);

		}
		custoCadastro.setCusto(custoCadastro.getCusto().add(mat.getPreco()));

	}

	public void remover(ItemMaterialCusto item) {

		int posicaoEncontrada = -1;

		for (int pos = 0; pos < itens.size() && posicaoEncontrada < 0; pos++) {
			ItemMaterialCusto itemTemp = itens.get(pos);

			if (itemTemp.getMaterial().getNome().equals(item.getMaterial().getNome())) {
				posicaoEncontrada = pos;
			}
		}

		if (posicaoEncontrada > -1) {
			itens.remove(posicaoEncontrada);
			custoCadastro.setCusto(custoCadastro.getCusto().subtract(item.getValorParcial()));

		}

	}

	public void carregarDados() {

		this.pegarProduto();

		custoCadastro.setData(new Date());

		ProdutoDAO pdao = new ProdutoDAO();
		produto = pdao.buscarPorCodigo(codigo);

		custoCadastro.setProduto(produto);

		custoCadastro.setCusto(custoCadastro.getCusto());

		BigDecimal parcial = produto.getFita().getPreco_metro().multiply(produto.getMetragem());
		custoCadastro.setCusto(custoCadastro.getCusto().add(parcial));
		custoCadastro.setMargem(custoCadastro.getProduto().getPrecoVenda().subtract(custoCadastro.getCusto()));

	}

	public void salvar() {

		try {
			CustoDAO cdao = new CustoDAO();
			cdao.salvar(custoCadastro);
			// System.out.println(custoCadastro.getProduto().getNome());
			// System.out.println(custoCadastro.getId());
			Custo custofk = cdao.buscarPorCodigo(custoCadastro.getId());

			for (ItemMaterialCusto item : itens) {
				item.setCusto(custofk);
				item.setMaterial(item.getMaterial());
				ItemMaterialCustoDAO itemdao = new ItemMaterialCustoDAO();
				itemdao.salvar(item);

			}

			custoCadastro = new Custo();
			custoCadastro.setCusto(new BigDecimal(0.00));
			itens = new ArrayList<ItemMaterialCusto>();

			JSFUtil.adicionarMensagemSucesso("Custo Salvo com sucesso");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());

		}
	}

	public void carregarProdutos() {

		ProdutoDAO pdao = new ProdutoDAO();
		produtos = (ArrayList<Produto>) pdao.listar();
	}

	public void pegarProduto() {

		try {

			String valor = JSFUtil.getParam("procod");
			if (valor != null) {

				int codigo = Integer.parseInt(valor);
				ProdutoDAO fdao = new ProdutoDAO();

				produto = fdao.buscarPorCodigo(codigo);

			} else {
				produto = new Produto();

			}

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

	// produtos com maior margem
	public void carregarMargem() {

		try {
			CustoDAO custodao = new CustoDAO();
			custos = (ArrayList<Custo>) custodao.listarMargem();

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @author Henrique Santiago henriquesantiagofranco@gmail.com
	 * @date 24/11/2018 - 00:41:25
	 *
	 * RN: Gera relatório de custos
	 *
	 * @throws Exception
	 */
	public void visualizarPDF() throws Exception {
		
		//Cria uma mapa que pode utilizado para enviar todos os parametros do relatório
		Map<String, Object> parametros = new HashMap<String, Object>();
		//parametros.put("logo",  ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/reports/".concat("invoice_logo.png")));
		
		//Define se o relatório será exibido no browser ou será feito download do arquivo gerado
		ReportUtils.contentDisposition = "";
		
		//Método com 3 parametros, mapa de parametros, o jasper do relatório e o nome do relatório que desejar
		ReportUtils.gerarRelatorio(parametros, "custos.jasper", "relatório de custos");
	}

}