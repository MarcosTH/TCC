package br.com.SolutionProd.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.SolutionProd.DAO.ItemNFDAO;
import br.com.SolutionProd.domain.ItemNF;
import br.com.SolutionProd.util.JSFUtil;

@ManagedBean(name = "MBItemNF")
@ViewScoped
public class ItemNFBean {
	
	private ItemNF itemnf;

	 private ArrayList<ItemNF>itens;
	 private ArrayList<ItemNF>itensFiltrados;
	 private String acao;
	 private Integer codigo;
	 
	 
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
	





	public ItemNF getItemnf() {
		 if(itemnf == null) {
				itemnf = new ItemNF();
				
			}
		return itemnf;
	}


	public void setItemnf(ItemNF itemnf) {
		this.itemnf = itemnf;
	}


	public ArrayList<ItemNF> getItens() {
		return itens;
	}


	public void setItens(ArrayList<ItemNF> itens) {
		this.itens = itens;
	}


	public ArrayList<ItemNF> getItensFiltrados() {
		return itensFiltrados;
	}


	public void setItensFiltrados(ArrayList<ItemNF> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}


	@PostConstruct
	 public void prepararPesquisa(){
		
	 try {
		 ItemNFDAO fdao = new ItemNFDAO();
	 itens = (ArrayList<ItemNF>) fdao.listar();
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("ex.getMessage()");
	 e.printStackTrace();
	 }
	
	 }
	 
	 
	 public void carregarCadastro(){

		 try {
		     
			
			 if(codigo != null){
				
				 String valor = JSFUtil.getParam("itnfcod");
				 int codigo = Integer.parseInt(valor);
				 ItemNFDAO idao = new ItemNFDAO();	
			
			itemnf = idao.buscarPorCodigo(codigo);
				 
			 }
			 else
				 {
				itemnf = new ItemNF();
				
			 }
			 
		
		 } catch (RuntimeException e) {
		 JSFUtil.adicionarMensagemErro("ex.getMessage()");
		 e.printStackTrace();
		 }
		
		 } 
	 

	 public void novo(){
	 itemnf = new ItemNF();
	 }

	public void salvar() {

		try {
			ItemNFDAO fdao = new ItemNFDAO();
			fdao.salvar(itemnf);
			
			itemnf = new ItemNF();

			 //itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("ItemNF salvo com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	
	
	 public void excluir(){
	 try {
		 ItemNFDAO fdao = new ItemNFDAO();
	 fdao.excluir(itemnf);
	
	
	
	 JSFUtil.adicionarMensagemSucesso("ItemNF excluido com sucesso!");
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("Não é possível excluir um ItemNF!");
	 e.printStackTrace();
	 }
	 }
	
	
	
	
	
	 public void editar(){
	 try {
		 ItemNFDAO fdao = new ItemNFDAO();
	 fdao.editar(itemnf);
	
	
	 JSFUtil.adicionarMensagemSucesso("ItemNF editado com sucesso!");
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("ex.getMessage()");
	 e.printStackTrace();
	 }
	 }
	
	
	

}
