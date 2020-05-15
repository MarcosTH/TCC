package br.com.SolutionProd.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.SolutionProd.DAO.FornecedorDAO;
import br.com.SolutionProd.DAO.FuncionarioDAO;
import br.com.SolutionProd.DAO.ItemMaterialCustoDAO;
import br.com.SolutionProd.DAO.ItemNFDAO;
import br.com.SolutionProd.DAO.MaterialDAO;
import br.com.SolutionProd.DAO.NFDAO;
import br.com.SolutionProd.domain.Custo;
import br.com.SolutionProd.domain.Fornecedor;
import br.com.SolutionProd.domain.Funcionario;
import br.com.SolutionProd.domain.ItemMaterialCusto;
import br.com.SolutionProd.domain.ItemNF;
import br.com.SolutionProd.domain.Material;
import br.com.SolutionProd.domain.NF;
import br.com.SolutionProd.domain.Produto;
import br.com.SolutionProd.util.JSFUtil;
import br.com.SolutionProd.util.ReportUtils;

@ManagedBean(name = "MBNF")
@ViewScoped
public class NFBean implements Serializable {

	private NF nf;

	private ArrayList<NF> itens;
	private ArrayList<NF> itensFiltrados;
	private String acao;
	private Integer codigo;
	private int id;
	private Fornecedor fornecedor;
	private List<Fornecedor> listaFornecedores;
	private ArrayList<ItemNF> itemNf;
	private ArrayList<ItemNF> itemNFFiltrados;
	private ArrayList<Material> materiais;
	private ArrayList<Material> materiaisFiltrados;
	private Material material;
	private int qtd;

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	private ArrayList<ItemNF> itemNFParaPesquisa;

	public ArrayList<ItemNF> getItemNFParaPesquisa() {
		return itemNFParaPesquisa;
	}

	public void setItemNFParaPesquisa(ArrayList<ItemNF> itemNFParaPesquisa) {
		this.itemNFParaPesquisa = itemNFParaPesquisa;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Fornecedor getFornecedor() {

		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		if (fornecedor == null) {
			fornecedor = new Fornecedor();
		}
		this.fornecedor = fornecedor;
	}

	public ArrayList<Material> getMateriais() {
		return materiais;
	}

	public void setMateriais(ArrayList<Material> materiais) {
		this.materiais = materiais;
	}

	public ArrayList<Material> getMateriaisFiltrados() {
		return materiaisFiltrados;
	}

	public void setMateriaisFiltrados(ArrayList<Material> materiaisFiltrados) {
		this.materiaisFiltrados = materiaisFiltrados;
	}

	public ArrayList<ItemNF> getItemNf() {
		if (itemNf == null) {
			itemNf = new ArrayList<>();
		}
		return itemNf;
	}

	public void setItemNf(ArrayList<ItemNF> itemNf) {
		this.itemNf = itemNf;
	}

	public ArrayList<ItemNF> getItemNFFiltrados() {
		return itemNFFiltrados;
	}

	public void setItemNFFiltrados(ArrayList<ItemNF> itemNFFiltrados) {
		this.itemNFFiltrados = itemNFFiltrados;
	}

	public List<Fornecedor> getListaFornecedores() {
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

	public NF getNf() {
		if (nf == null) {
			nf = new NF();
			nf.setTotal(new BigDecimal(0.00));

		}
		return nf;
	}

	public void setNf(NF nf) {
		this.nf = nf;
	}

	public ArrayList<NF> getItens() {
		return itens;
	}

	public void setItens(ArrayList<NF> itens) {
		this.itens = itens;
	}

	public ArrayList<NF> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<NF> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa() {

		try {
			NFDAO fdao = new NFDAO();
			itens = (ArrayList<NF>) fdao.listar();

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

	public void carregarCadastro() {

		try {

			if (codigo != null) {

				String valor = JSFUtil.getParam("nfcod");
				int codigo = Integer.parseInt(valor);
				NFDAO fdao = new NFDAO();

				nf = fdao.buscarPorCodigo(codigo);

			} else {
				nf = new NF();

			}

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

	public void novo() {
		nf = new NF();
		fornecedor = new Fornecedor();
	}

	public void salvar() {

		try {
			NFDAO fdao = new NFDAO();
			fdao.salvar(nf);
			NF nffk = fdao.buscarPorCodigo(nf.getId());
			for (ItemNF item : itemNf) {
				item.setNf(nffk);
				item.setMaterial(item.getMaterial());
				ItemNFDAO itemdao = new ItemNFDAO();
				itemdao.salvar(item);
				MaterialDAO mdao = new MaterialDAO();
				Material material1;
				material1 = mdao.buscarPorID(item.getMaterial().getId());
				material1.setQuantidade(material1.getQuantidade() + item.getQuantidade());
				mdao.merge(material1);
				this.novo();

			}
			this.setId(nf.getId());
			nf = new NF();

			itemNf = new ArrayList<ItemNF>();

			JSFUtil.adicionarMensagemSucesso("NF salvo com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			nf = (NF) evento.getComponent().getAttributes().get("nfSelecionada");

			NFDAO fdao = new NFDAO();
			fdao.excluir(nf);

			itens = (ArrayList<NF>) fdao.listar();

			Messages.addGlobalInfo("Nota fiscal removida com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a Nota fiscal");
			erro.printStackTrace();
		}
	}

	public void editar() {
		try {
			NFDAO fdao = new NFDAO();
			fdao.editar(nf);

			JSFUtil.adicionarMensagemSucesso("NF editado com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void adicionarMaterial(Material mat) {
		BigDecimal valor;
		int posicaoEncontrada = -1;
		for (int pos = 0; pos < itemNf.size() && posicaoEncontrada < 0; pos++) {
			ItemNF itemTemp = itemNf.get(pos);

			if (itemTemp.getMaterial().getNome().equals(mat.getNome())) {
				posicaoEncontrada = pos;
			}
		}

		ItemNF item = new ItemNF();
		item.setMaterial(mat);

		if (posicaoEncontrada < 0) {

			item.setQuantidade(100);
			item.setValorParcial(mat.getPreco());
			itemNf.add(item);
			nf.setTotal(nf.getTotal().add(item.getValorParcial()));
		} else {

			ItemNF itemTemp = itemNf.get(posicaoEncontrada);
			item.setQuantidade(itemTemp.getQuantidade() + 100);
			item.setValorParcial(mat.getPreco().multiply(new BigDecimal(item.getQuantidade())));

			itemNf.set(posicaoEncontrada, item);
			calcular();

		}
		// valor = (mat.getPreco().multiply(new BigDecimal(item.getQuantidade())));

		// nf.setTotal(item.getValorParcial());

	}

	public void remover(ItemNF item) {

		int posicaoEncontrada = -1;

		for (int pos = 0; pos < itemNf.size() && posicaoEncontrada < 0; pos++) {
			ItemNF itemTemp = itemNf.get(pos);

			if (itemTemp.getMaterial().getNome().equals(item.getMaterial().getNome())) {
				posicaoEncontrada = pos;

			}

		}

		if (posicaoEncontrada > -1) {
			itemNf.remove(posicaoEncontrada);
			// ItemNF itemTemp = itemNf.get((posicaoEncontrada));
			// BigDecimal parcial = itemTemp.getValorParcial().multiply(new
			// BigDecimal(item.getQuantidade()));
			// nf.setTotal(nf.getTotal().subtract(parcial));

		}

		calcular();

	}

	public void calcular() {
		nf.setTotal((new BigDecimal("0.00")));

		for (int posicao = 0; posicao < itemNf.size(); posicao++) {
			ItemNF itemVenda = itemNf.get(posicao);
			nf.setTotal(nf.getTotal().add(itemVenda.getValorParcial()));
		}
	}

	public void carregarDados() {

		material = materiais.get(1);
		nf.setData(nf.getData());
	
		nf.setFornecedor(material.getFornecedor());

	}

	public void carregarMateriais() {

		try {

			MaterialDAO dao = new MaterialDAO();
			materiais = (ArrayList<Material>) dao.listar();
			FornecedorDAO fdao = new FornecedorDAO();
			listaFornecedores = fdao.listar();

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

	public void carregarMateriaisNF() {

		try {

			// array recebe todas as notas e filtra por fornecedor
			NFDAO nfdao = new NFDAO();
			itens = (ArrayList<NF>) nfdao.listar();
			for (NF notas : itens) {
				if (notas.getFornecedor().getId() == fornecedor.getId()) {
					itensFiltrados.add(notas);

				}

			}

			// filtra os itens por codigo da nf
			ItemNFDAO itemdao = new ItemNFDAO();
			ArrayList<ItemNF> itensTemp = (ArrayList<ItemNF>) itemdao.listar();

			for (ItemNF itemTemp : itensTemp) {
				if (itemTemp.getNf().getId() == 6) {
				}
				itemNFParaPesquisa.add(itemTemp);
			}

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

	public void carregarFornecedores() {
		FornecedorDAO fdao = new FornecedorDAO();
		listaFornecedores = fdao.listar();

	}
	
	public void visualizarPDF2() throws Exception {

		// Cria uma mapa que pode utilizado para enviar todos os parametros do relatório
		Map<String, Object> parametros = new HashMap<String, Object>();
		// parametros.put("logo", ((ServletContext)
		// FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/reports/".concat("invoice_logo.png")));
		parametros.put("Parameter2",6);
		// Define se o relatório será exibido no browser ou será feito download do
		// arquivo gerado
  	ReportUtils.contentDisposition = "";

		// Método com 3 parametros, mapa de parametros, o jasper do relatório e o nome
		// do relatório que desejar
		ReportUtils.gerarRelatorio(parametros, "pedido.jasper", "relatório de pedido");
		
	}

}
