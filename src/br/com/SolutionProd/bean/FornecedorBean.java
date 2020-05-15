package br.com.SolutionProd.bean;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;

import br.com.SolutionProd.DAO.FornecedorDAO;
import br.com.SolutionProd.domain.Fornecedor;
import br.com.SolutionProd.domain.Funcionario;
import br.com.SolutionProd.util.HibernateUtil;
import br.com.SolutionProd.util.JSFUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;



@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedorBean {

	private Fornecedor fornecedores;

	 private ArrayList<Fornecedor>itens;
	 private ArrayList<Fornecedor>itensFiltrados;
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
	
	public Fornecedor getFornecedores() {
		if(fornecedores == null) {
			fornecedores = new Fornecedor();
			
		}
			
		
		
		return fornecedores;
	}

	
	public void setFornecedores(Fornecedor fornecedores) {
		this.fornecedores = fornecedores;
	}

	 public ArrayList<Fornecedor> getItens() {
	 return itens;
	 }
	
	 public void setItens(ArrayList<Fornecedor> itens) {
	 this.itens = itens;
	 }
	
	
	 public ArrayList<Fornecedor> getItensFiltrados() {
	 return itensFiltrados;
	 }
	
	 public void setItensFiltrados(ArrayList<Fornecedor> itensFiltrados) {
	 this.itensFiltrados = itensFiltrados;
	 }

	 @PostConstruct
	 public void prepararPesquisa(){
		
	 try {
	 FornecedorDAO fdao = new FornecedorDAO();
	 itens = (ArrayList<Fornecedor>) fdao.listar();
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro(e.getMessage());
	 e.printStackTrace();
	 }
	
	 }
	 
	 
	 public void carregarCadastro(){

		 try {
		     
			
			 if(codigo != null){
				
				 String valor = JSFUtil.getParam("forcod");
				 int codigo = Integer.parseInt(valor);
			FornecedorDAO fdao = new FornecedorDAO();	
			
			fornecedores = fdao.buscarPorCodigo(codigo);
				 
			 }
			 else
				 {
				fornecedores = new Fornecedor();
				
			 }
			 
		
		 } catch (RuntimeException e) {
		 JSFUtil.adicionarMensagemErro(e.getMessage());
		 e.printStackTrace();
		 }
		
		 } 
	 

	 public void novo(){
	 fornecedores = new Fornecedor();
	 this.acao = "novo";
	 }

	public void salvar() {

		try {
			FornecedorDAO fdao = new FornecedorDAO();
			fdao.salvar(fornecedores);
			
			fornecedores = new Fornecedor();

			 //itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Fornecedor salvo com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	
	
	 public void excluir(){
	 try {
	 FornecedorDAO fdao = new FornecedorDAO();
	 fdao.excluir(fornecedores);
	
	
	
	 JSFUtil.adicionarMensagemSucesso("Fornecedor excluido com sucesso!");
	
	 } catch (RuntimeException e) {
	// JSFUtil.adicionarMensagemErro("Não é possível excluir um fornecedor que tenha um produto associado!");
	 e.printStackTrace();
	 }
	 }
	
	
	
	
	
	 public void editar(){
	 try {
	 FornecedorDAO fdao = new FornecedorDAO();
	 fdao.editar(fornecedores);
	
	
	 JSFUtil.adicionarMensagemSucesso("Fornecedor editado com sucesso!");
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro(e.getMessage());
	 e.printStackTrace();
	 }
	 }
	 
 public void imprimir() {
	 

try {
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent

("formListagem:tabela");
			Map<String, Object> filtros = tabela.getFilters();

			String proDescricao = (String) filtros.get("descricao");
			String forDescricao = (String) filtros.get("fornecedor.descricao");

			String caminho = Faces.getRealPath("/reports/produto.jasper");

			Map<String, Object> parametros = new HashMap<>();
			if (proDescricao == null) {
				parametros.put("PRODUTO_DESCRICAO", "%%");
			} else {
				parametros.put("PRODUTO_DESCRICAO", "%" + proDescricao + "%");
			}
			if (forDescricao == null) {
				parametros.put("FORNECEDOR_DESCRICAO", "%%");
			} else {
				parametros.put("FORNECEDOR_DESCRICAO", "%" + forDescricao + 

"%");
			}

			Connection conexao = HibernateUtil.getConexao();

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, 

parametros, conexao);

			JasperPrintManager.printReport(relatorio, true);

} catch (JRException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
			erro.printStackTrace();
		}
	}


 }
	 
	 
	


