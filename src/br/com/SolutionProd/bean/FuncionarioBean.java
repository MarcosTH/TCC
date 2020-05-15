package br.com.SolutionProd.bean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.SolutionProd.DAO.FuncionarioDAO;
import br.com.SolutionProd.domain.Fornecedor;
import br.com.SolutionProd.domain.Funcionario;
import br.com.SolutionProd.util.JSFUtil;


@ManagedBean(name = "MBFuncionarios")
@ViewScoped
public class FuncionarioBean implements Serializable {
	
	private Funcionario funcionarios;

	 private ArrayList<Funcionario>itens;
	 private ArrayList<Funcionario>itensFiltrados;
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
	
	public Funcionario getFuncionarios() {
		if(funcionarios == null) {
			funcionarios = new Funcionario();
			
		}
		
		return funcionarios;
	}

	
	public void setFornecedores(Funcionario funcionarios) {
		this.funcionarios = funcionarios;
	}

	 public ArrayList<Funcionario> getItens() {
	 return itens;
	 }
	
	 public void setItens(ArrayList<Funcionario> itens) {
	 this.itens = itens;
	 }
	
	
	 public ArrayList<Funcionario> getItensFiltrados() {
	 return itensFiltrados;
	 }
	
	 public void setItensFiltrados(ArrayList<Funcionario> itensFiltrados) {
	 this.itensFiltrados = itensFiltrados;
	 }

	 @PostConstruct
	 public void prepararPesquisa(){
		
	 try {
	 FuncionarioDAO fdao = new FuncionarioDAO();
	 itens = (ArrayList<Funcionario>) fdao.listar();
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro(e.getMessage());
	 e.printStackTrace();
	 }
	
	 }
	 
	 
	 public void carregarCadastro(){

		 try {
			 String valor = JSFUtil.getParam("funcod");
			
			 if(valor != null){
				
			
				 int codigo = Integer.parseInt(valor);
				 FuncionarioDAO fdao = new FuncionarioDAO();	
			
			funcionarios = fdao.buscarPorCodigo(codigo);
				 
			 }
			 else
				 {
				funcionarios = new Funcionario();
				
			 }
			 
		
		 } catch (RuntimeException e) {
		 JSFUtil.adicionarMensagemErro(e.getMessage());
		 e.printStackTrace();
		 }
		
		 } 
	 

	 public void novo(){
	 funcionarios = new Funcionario();
	 }

	public void salvar() {

		try {
			FuncionarioDAO fdao = new FuncionarioDAO();
			fdao.salvar(funcionarios);
			
			funcionarios = new Funcionario();

			 //itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Funcionario salvo com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	
	
	 public void excluir(){
	 try {
		 FuncionarioDAO fdao = new FuncionarioDAO();
	 fdao.excluir(funcionarios);
	
	
	
	 JSFUtil.adicionarMensagemSucesso("Funcionario excluido com sucesso!");
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("Não é possível excluir um funcionario que tenha um material associado!");
	 e.printStackTrace();
	 }
	 }
	
	
	
	
	
	 public void editar(){
	 try {
		 FuncionarioDAO fdao = new FuncionarioDAO();
	 fdao.editar(funcionarios);
	
	
	 JSFUtil.adicionarMensagemSucesso("Funcionario editado com sucesso!");
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro(e.getMessage());
	 e.printStackTrace();
	 }
	 }
	
	
	

}
