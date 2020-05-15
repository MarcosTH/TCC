package br.com.SolutionProd.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.SolutionProd.DAO.FitaDAO;
import br.com.SolutionProd.DAO.FornecedorDAO;
import br.com.SolutionProd.DAO.ProdutoDAO;
import br.com.SolutionProd.domain.Fita;
import br.com.SolutionProd.domain.Fornecedor;
import br.com.SolutionProd.domain.Produto;
import br.com.SolutionProd.util.JSFUtil;


@ManagedBean(name = "MBProdutos")
@ViewScoped
public class ProdutoBean {
	
	private Produto produtos;
	 private ArrayList<Produto>margem;
	 private ArrayList<Produto>itens;
	 private ArrayList<Produto>itensFiltrados;
	 private String acao;
	 private Integer codigo;	 
	 private List <Fita> listaDeFitas ;
	 
	 
	 public ArrayList<Produto> getMargem() {
		return margem;
	}


	public void setMargem(ArrayList<Produto> margem) {
		this.margem = margem;
	}


	public List<Fita> getListaDeFitas() {
		 FitaDAO fdao = new FitaDAO();
		 listaDeFitas = fdao.listar();
			return listaDeFitas;
		
	}


	public void setListaDeFitas(List<Fita> listaDeFitas) {
		this.listaDeFitas = listaDeFitas;
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
	
	public Produto getProdutos() {
		if(produtos == null) {
			produtos = new Produto();
			
		}
		
		return produtos;
	}

	//
	public void setProdutos(Produto produto) {
		this.produtos = produto;
	}

	 public ArrayList<Produto> getItens() {
	 return itens;
	 }
	
	 public void setItens(ArrayList<Produto> itens) {
	 this.itens = itens;
	 }
	
	
	 public ArrayList<Produto> getItensFiltrados() {
	 return itensFiltrados;
	 }
	
	 public void setItensFiltrados(ArrayList<Produto> itensFiltrados) {
	 this.itensFiltrados = itensFiltrados;
	 }

	 @PostConstruct
	 public void prepararPesquisa(){
		
	 try {
		 ProdutoDAO fdao = new ProdutoDAO();
	 itens = (ArrayList<Produto>) fdao.listar();
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro(e.getMessage());
	 e.printStackTrace();
	 }
	
	 }
	 
	 
	 @PostConstruct
	 public void prepararPesquisaMargem(){
		
	 try {
		 ProdutoDAO fdao = new ProdutoDAO();
	 itens = (ArrayList<Produto>) fdao.listar();

	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro(e.getMessage());
	 e.printStackTrace();
	 }
	
	 }
	
	 
	 
	 public void carregarCadastro(){

		 try {
		     
			
			 if(codigo != null){
				
				 String valor = JSFUtil.getParam("procod");
				 int codigo = Integer.parseInt(valor);
				 ProdutoDAO fdao = new ProdutoDAO();	
			
			produtos = fdao.buscarPorCodigo(codigo);
				 
			 }
			 else
				 {
				produtos = new Produto();
				
			 }
			 
		
		 } catch (RuntimeException e) {
		 JSFUtil.adicionarMensagemErro(e.getMessage());
		 e.printStackTrace();
		 }
		
		 } 
	 

	 public void novo(){
	 produtos = new Produto();
	 }

	public void salvar() {

		try {
			ProdutoDAO fdao = new ProdutoDAO();
			fdao.salvar(produtos);
			
			produtos = new Produto();

			 //itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Produto salvo com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	
	
	 public void excluir(){
	 try {
		 ProdutoDAO fdao = new ProdutoDAO();
	 fdao.excluir(produtos);
	
	
	
	 JSFUtil.adicionarMensagemSucesso("Produto excluido com sucesso!");
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("Não é possível excluir o Produto!");
	 e.printStackTrace();
	 }
	 }
	
	
	 public void editar(){
	 try {
		 ProdutoDAO fdao = new ProdutoDAO();
	 fdao.editar(produtos);
	
	
	 JSFUtil.adicionarMensagemSucesso("Produto editado com sucesso!");
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro(e.getMessage());
	 e.printStackTrace();
	 }
	 }
	
	
	

}
