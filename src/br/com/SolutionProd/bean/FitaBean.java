package br.com.SolutionProd.bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.SolutionProd.DAO.FitaDAO;
import br.com.SolutionProd.DAO.FornecedorDAO;
import br.com.SolutionProd.domain.Fita;
import br.com.SolutionProd.domain.Material;
import br.com.SolutionProd.util.JSFUtil;

@ManagedBean(name = "MBFitas")
@ViewScoped
public class FitaBean {
	
	

	private Fita fita;

	 private ArrayList<Fita>itens;
	 private ArrayList<Fita>itensFiltrados;
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
	


	public Fita getFita() {
		if(fita == null) {
			fita = new Fita();
			
		}
		return fita;
	}


	public void setFita(Fita fita) {
		this.fita = fita;
	}


	public ArrayList<Fita> getItens() {
		return itens;
	}


	public void setItens(ArrayList<Fita> itens) {
		this.itens = itens;
	}


	public ArrayList<Fita> getItensFiltrados() {
		return itensFiltrados;
	}


	public void setItensFiltrados(ArrayList<Fita> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}


	// @PostConstruct
	 public void prepararPesquisa(){
		
	 try {
	 FitaDAO fdao = new FitaDAO();
	 itens = (ArrayList<Fita>) fdao.listar();
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("ex.getMessage()");
	 e.printStackTrace();
	 }
	
	 }
	 
	 
	 public void carregarCadastro(){

		 try {
			 String valor = JSFUtil.getParam("fitcod");
			
			 if(valor != null){
				
			
				 int codigo = Integer.parseInt(valor);
			FitaDAO fdao = new FitaDAO();	
			
			fita = fdao.buscarPorCodigo(codigo);
				 
			 }
			 else
				 {
				fita = new Fita();
				
			 }
			 
		
		 } catch (RuntimeException e) {
		 JSFUtil.adicionarMensagemErro("ex.getMessage()");
		 e.printStackTrace();
		 }
		
		 } 
	 

	 public void novo(){
	 fita = new Fita();
	 }

	public void salvar() {

		try {
			FitaDAO fdao = new FitaDAO();
			fdao.salvar(fita);
			
			fita = new Fita();

			 //itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Fita salva com sucesso!");

		} catch (RuntimeException e) {
		//	JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	
	
	 public void excluir(){
	 try {
	 FitaDAO fdao = new FitaDAO();
	 fdao.excluir(fita);
	
	
	
	 JSFUtil.adicionarMensagemSucesso("Fita excluída com sucesso!");
	
	 } catch (RuntimeException e) {
	// JSFUtil.adicionarMensagemErro("Não é possível excluir um fornecedor que tenha um produto associado!");
	 e.printStackTrace();
	 }
	 }
	
	
	
	
	
	 public void editar(){
	 try {
		 FitaDAO fdao = new FitaDAO();
	 fdao.editar(fita);
	
	
	 JSFUtil.adicionarMensagemSucesso("Fita editada com sucesso!");
	
	 } catch (RuntimeException e) {
	// JSFUtil.adicionarMensagemErro("ex.getMessage()");
	 e.printStackTrace();
	 }
	 }
	
}



