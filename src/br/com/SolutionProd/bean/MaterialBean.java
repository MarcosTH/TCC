package br.com.SolutionProd.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.SolutionProd.DAO.CustoDAO;
import br.com.SolutionProd.DAO.FornecedorDAO;
import br.com.SolutionProd.DAO.FuncionarioDAO;
import br.com.SolutionProd.DAO.ItemMaterialCustoDAO;
import br.com.SolutionProd.DAO.MaterialDAO;
import br.com.SolutionProd.DAO.ProdutoDAO;
import br.com.SolutionProd.domain.Custo;
import br.com.SolutionProd.domain.Fornecedor;
import br.com.SolutionProd.domain.Funcionario;
import br.com.SolutionProd.domain.ItemMaterialCusto;
import br.com.SolutionProd.domain.Material;
import br.com.SolutionProd.domain.Produto;
import br.com.SolutionProd.util.JSFUtil;
import br.com.SolutionProd.util.ReportUtils;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@ManagedBean(name = "MBMateriais")
@ViewScoped
public class MaterialBean {

	private Material materiais;

	private ArrayList<Material> itens;
	private ArrayList<Material> itensFiltrados;
	private String acao;
	private Integer codigo;
	private List<Fornecedor> listaFornecedores;

	public List<Fornecedor> getListaFornecedores() {
		FornecedorDAO fdao = new FornecedorDAO();
		listaFornecedores = fdao.listar();
		return listaFornecedores;
	}

	public void setListaFornecedores(List<Fornecedor> listaFornecedores) {
		this.listaFornecedores = listaFornecedores;
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

	public Material getMateriais() {
		if (materiais == null) {
			materiais = new Material();

		}

		return materiais;
	}

	public void setMateriais(Material materiais) {
		this.materiais = materiais;
	}

	public ArrayList<Material> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Material> itens) {
		this.itens = itens;
	}

	public ArrayList<Material> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Material> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa() {

		try {
			MaterialDAO fdao = new MaterialDAO();
			itens = (ArrayList<Material>) fdao.listar();

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

	@PostConstruct
	public void prepararPesquisaFaltas() {

		try {
			MaterialDAO fdao = new MaterialDAO();
			itens = (ArrayList<Material>) fdao.listarFaltas();

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

	public void carregarCadastro() {

		try {

			String valor = JSFUtil.getParam("matcod");
			if (valor != null) {

				
				int codigo = Integer.parseInt(valor);
				MaterialDAO mdao = new MaterialDAO();

				materiais = mdao.buscarPorID(codigo);

			} else {
				materiais = new Material();

			}

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

	public void novo() {
		materiais = new Material();
	}

	public void salvar() {

		try {
			MaterialDAO mdao = new MaterialDAO();

			
			
			mdao.salvar(materiais);
			
			materiais = new Material();

			// itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Material salvo com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {
			MaterialDAO fdao = new MaterialDAO();
			fdao.excluir(materiais);

			JSFUtil.adicionarMensagemSucesso("Material excluido com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("Não é possível excluir um Material!");
			e.printStackTrace();
		}
	}

	public void editar() {
		try {
			MaterialDAO mdao = new MaterialDAO();
			
			mdao.editar(materiais);
			this.editarPrecoCusto();
			JSFUtil.adicionarMensagemSucesso("Material editado com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void editarPrecoCusto() {
		Material material = new Material();
		MaterialDAO mdao = new MaterialDAO();
		Produto produto = new Produto();
		ProdutoDAO pdao = new ProdutoDAO();
		Custo custo = new Custo();
		CustoDAO cdao = new CustoDAO();
		ItemMaterialCustoDAO itemdao = new ItemMaterialCustoDAO();
		// ArrayList<ItemMaterialCusto> itens = (ArrayList<ItemMaterialCusto>)
		// itemdao.listarPorCusto(custo.getId());

		for (Custo custo1 : cdao.listar()) {
			//custo1.setCusto(new BigDecimal(0.00));
			BigDecimal valorAntigo = new BigDecimal(0.00);
			BigDecimal valorParcialNovo = new BigDecimal(0.00);
			ArrayList<ItemMaterialCusto> itens = (ArrayList<ItemMaterialCusto>) itemdao.listarPorCusto(custo1.getId());
			for (ItemMaterialCusto item : itens) {
				if (item.getMaterial().getId() == materiais.getId()) {
					item.setValorParcial(materiais.getPreco().multiply(new BigDecimal(item.getQuantidade())));										
					valorParcialNovo =(valorParcialNovo).add(item.getValorParcial());													
				
					System.out.println("valor parcial , dentro do if do segundo for" + valorParcialNovo);
					System.out.println("valor item , dentro do if do segundo for" + item.getValorParcial());
				}else {
					valorAntigo = (valorAntigo).add(item.getValorParcial());
					System.out.println("valor parcial , dentro do segundo for" + valorAntigo);
				}
				itemdao.editar(item);				
			}
			BigDecimal parcial = custo1.getProduto().getFita().getPreco_metro().multiply(custo1.getProduto().getMetragem());
			custo1.setCusto(((valorAntigo).add(valorParcialNovo)).add(parcial)  ) ;
			custo1.setMargem(custo1.getProduto().getPrecoVenda().subtract(custo1.getCusto()));
			cdao.editar(custo1);
			System.out.println(custo1.getProduto().getNome());
			System.out.println(custo1.getId());
			System.out.println(custo1.getCusto());
			
		}

	}

	public void visualizarPDF() throws Exception {

		// Cria uma mapa que pode utilizado para enviar todos os parametros do relatório
		Map<String, Object> parametros = new HashMap<String, Object>();
		// parametros.put("logo", ((ServletContext)
		// FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/reports/".concat("invoice_logo.png")));

		// Define se o relatório será exibido no browser ou será feito download do
		// arquivo gerado
		ReportUtils.contentDisposition = "";

		// Método com 3 parametros, mapa de parametros, o jasper do relatório e o nome
		// do relatório que desejar
		ReportUtils.gerarRelatorio(parametros, "MateriaisBaixa.jasper", "relatório de custos");
	}

	

}
