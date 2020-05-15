package br.com.SolutionProd.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.SolutionProd.DAO.ItemMaterialCustoDAO;
import br.com.SolutionProd.DAO.NFDAO;
import br.com.SolutionProd.domain.ItemMaterialCusto;
import br.com.SolutionProd.domain.NF;
import br.com.SolutionProd.util.JSFUtil;

@ManagedBean(name = "MBItemMaterialCusto")
@ViewScoped
public class ItemMaterialCustoBean {
	
	private ItemMaterialCusto item;

	 private ArrayList<ItemMaterialCusto>itens;
	 private ArrayList<ItemMaterialCusto>itensFiltrados;
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
	


	public ItemMaterialCusto getItem() {
		if(item == null) {
			item = new ItemMaterialCusto();
			
		}
		return item;
	}


	public void setItem(ItemMaterialCusto item) {
		this.item = item;
	}


	public ArrayList<ItemMaterialCusto> getItens() {
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


	@PostConstruct
	 public void prepararPesquisa(){
		
	 try {
		 ItemMaterialCustoDAO fdao = new ItemMaterialCustoDAO();
	 itens = (ArrayList<ItemMaterialCusto>) fdao.listar();
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("ex.getMessage()");
	 e.printStackTrace();
	 }
	
	 }
	 
	 
	 public void carregarCadastro(){

		 try {
		     
			
			 if(codigo != null){
				
				 String valor = JSFUtil.getParam("itemmatcustcod");
				 int codigo = Integer.parseInt(valor);
				 ItemMaterialCustoDAO fdao = new ItemMaterialCustoDAO();	
			
			item = fdao.buscarPorCodigo(codigo);
				 
			 }
			 else
				 {
				item = new ItemMaterialCusto();
				
			 }
			 
		
		 } catch (RuntimeException e) {
		 JSFUtil.adicionarMensagemErro("ex.getMessage()");
		 e.printStackTrace();
		 }
		
		 } 
	 

	 public void novo(){
	 item = new ItemMaterialCusto();
	 }

	public void salvar() {

		try {
			ItemMaterialCustoDAO fdao = new ItemMaterialCustoDAO();
			fdao.salvar(item);
			
			item = new ItemMaterialCusto();

			 //itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("ItemMaterialCusto salvo com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	
	
	 public void excluir(){
	 try {
		 ItemMaterialCustoDAO fdao = new ItemMaterialCustoDAO();
	 fdao.excluir(item);
	
	
	
	 JSFUtil.adicionarMensagemSucesso("ItemMaterialCusto excluido com sucesso!");
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("Não é possível excluir um Item!");
	 e.printStackTrace();
	 }
	 }
	
	
	
	
	
	 public void editar(){
	 try {
		 ItemMaterialCustoDAO fdao = new ItemMaterialCustoDAO();
	 fdao.editar(item);
	
	
	 JSFUtil.adicionarMensagemSucesso("ItemMaterialCusto editado com sucesso!");
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("ex.getMessage()");
	 e.printStackTrace();
	 }
	 }
	
	
	

}

