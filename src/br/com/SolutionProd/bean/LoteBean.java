package br.com.SolutionProd.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
import br.com.SolutionProd.util.JSFUtil;
import br.com.SolutionProd.util.ReportUtils;

@ManagedBean(name = "MBLotes")
@ViewScoped
public class LoteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Lote lotes;

	private ArrayList<Lote> itens;
	private ArrayList<Lote> itensFiltrados;
	private String acao;
	private Integer codigo;
	private Produto produto;
	private List<Produto> produtos;
	private List<Funcionario> funcionarios;
	private ArrayList<Produto> produtosFiltrados;
	private Funcionario funcionario;
	private int quantidade;
	private String qtd;

	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public String getQtd() {
		return qtd;
	}

	public void setQtd(String qtd) {
		this.qtd = qtd;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Funcionario getFuncionario() {
		AutenticacaoBean a = new AutenticacaoBean();
		funcionario = a.getUsuarioLogado().getFuncionario();
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public ArrayList<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public void setProdutosFiltrados(ArrayList<Produto> produtosFiltrados) {
		this.produtosFiltrados = produtosFiltrados;
	}

	public Produto getProduto() {

		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		ProdutoDAO pdao = new ProdutoDAO();
		produtos = (ArrayList<Produto>) pdao.listar();
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Lote getLotes() {
		if (lotes == null) {
			lotes = new Lote();

		}

		return lotes;
	}

	public void setLotes(Lote lotes) {
		this.lotes = lotes;
	}

	public ArrayList<Lote> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Lote> itens) {
		this.itens = itens;
	}

	public ArrayList<Lote> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Lote> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public void prepararPesquisa() {

		try {
			LoteDAO fdao = new LoteDAO();
			itens = (ArrayList<Lote>) fdao.listar();

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());

		}

	}

	public void carregarCadastro() {

		try {

			if (codigo != null) {
				String valor = JSFUtil.getParam("procod");

				int codigo = Integer.parseInt(valor);
				ProdutoDAO fdao = new ProdutoDAO();

				produto = fdao.buscarPorCodigo(codigo);
				System.out.println(produto.getNome());
				System.out.println(codigo);

			} else {
				lotes = new Lote();
				System.out.println("caiu aqui");

			}

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

	public void novo() {
		lotes = new Lote();
	}

	public void salvar() {

		try {

			System.out.println(lotes.getProduto().getNome());
			System.out.println(lotes.getQuantidade());

			LoteDAO ldao = new LoteDAO();

			ldao.salvar(lotes);
			JSFUtil.adicionarMensagemSucesso("Lote salvo com sucesso!");
			// chamando o metodo de atualizar estoque
			this.atualizarEstoque();

			lotes = new Lote();
		} catch (Exception e) {
			JSFUtil.adicionarMensagemSucesso("Erro ao salvar lote, verifique se o produto possui custo calculado!");
		}

	}

	public void excluir() {
		try {
			LoteDAO fdao = new LoteDAO();
			fdao.excluir(lotes);

			JSFUtil.adicionarMensagemSucesso("Lote excluido com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("Não é possível excluir o Lote!");
			e.printStackTrace();
		}
	}

	public void editar() {
		try {
			LoteDAO fdao = new LoteDAO();
			fdao.editar(lotes);

			JSFUtil.adicionarMensagemSucesso("Lote editado com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void carregarProdutos() {

		try {

			ProdutoDAO dao = new ProdutoDAO();
			produtos = (ArrayList<Produto>) dao.listar();

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void carregarFuncionarios() {

		try {

			FuncionarioDAO dao = new FuncionarioDAO();
			funcionarios = (ArrayList<Funcionario>) dao.listar();

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}


	public void atualizarEstoque() {

		Material material = new Material();
		MaterialDAO mdao = new MaterialDAO();
		Produto produto = new Produto();
		ProdutoDAO pdao = new ProdutoDAO();
		produto = pdao.buscarPorCodigo(lotes.getProduto().getId());
		Custo custo = new Custo();
		CustoDAO cdao = new CustoDAO();
		custo = cdao.buscarPorProduto(produto.getId());
		custo = cdao.buscarPorProduto(produto.getId());
		ItemMaterialCustoDAO itemdao = new ItemMaterialCustoDAO();
		ArrayList<ItemMaterialCusto> itens = (ArrayList<ItemMaterialCusto>) itemdao.listarPorCusto(custo.getId());

		for (ItemMaterialCusto item : itens) {
			material = item.getMaterial();
			material.setQuantidade(material.getQuantidade() - (item.getQuantidade() * lotes.getQuantidade()));
			mdao.editar(material);

		}

	}
	
public void visualizarPDF() throws Exception {
		
		//Cria uma mapa que pode utilizado para enviar todos os parametros do relatório
		Map<String, Object> parametros = new HashMap<String, Object>();
		//parametros.put("logo",  ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/reports/".concat("logo.png")));
	
		//Define se o relatório será exibido no browser ou será feito download do arquivo gerado
		ReportUtils.contentDisposition = "";
		
		//Método com 3 parametros, mapa de parametros, o jasper do relatório e o nome do relatório que desejar
		ReportUtils.gerarRelatorio(parametros, "Lotes.jasper", "relatório de lucro");
	}
	
	

}
