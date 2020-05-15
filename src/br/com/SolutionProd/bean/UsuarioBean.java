package br.com.SolutionProd.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.SolutionProd.DAO.FitaDAO;
import br.com.SolutionProd.DAO.FornecedorDAO;
import br.com.SolutionProd.DAO.FuncionarioDAO;
import br.com.SolutionProd.DAO.UsuarioDAO;
import br.com.SolutionProd.domain.Fita;
import br.com.SolutionProd.domain.Funcionario;
import br.com.SolutionProd.domain.Usuario;
import br.com.SolutionProd.util.JSFUtil;

@ManagedBean(name = "UsuarioMB")
@ViewScoped
public class UsuarioBean implements Serializable {
  
	private Usuario usuario;
	private Usuario usuarioLogado;
	private Funcionario funcionario;
	private List<Funcionario> funcionarios;
	private List<Usuario> usuarios;
	private String acao;
	 private Integer codigo;

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Funcionario getFuncionario() {
		if(funcionario == null) {
			funcionario = new Funcionario();
		}
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Usuario getUsuario() {
		if(usuario ==null) {
			usuario = new Usuario();
			
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Funcionario> getFuncionarios() {
		FuncionarioDAO funDAO = new FuncionarioDAO();
		funcionarios = funDAO.listar();
		
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	@PostConstruct
	public void listar(){
		try{
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar();
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuários");
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		try {
			usuario = new Usuario();
			FuncionarioDAO funDAO = new FuncionarioDAO();
			funcionarios = funDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo usuário");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			SimpleHash hash = new SimpleHash("md5", usuario.getSenha());
			usuario.setSenha(hash.toHex());
			usuario.setAtivo(true);
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);
			usuario = new Usuario();
			usuarios = usuarioDAO.listar();
			
			FuncionarioDAO funDAO = new FuncionarioDAO();
			funcionarios = funDAO.listar();
			
			Messages.addGlobalInfo("Usuário salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o usuário");
			erro.printStackTrace();
		}
	}
	
	
	public void carregarFuncionarios() {
		
		FuncionarioDAO funDAO = new FuncionarioDAO();
		funcionarios = funDAO.listar();
		
	}
	
	
	public void excluir() {
		
		
	}
	
	
	 public void carregarCadastro(){

		 try {
			 String valor = JSFUtil.getParam("usucod");
			
			 if(valor != null){
				
			
				 int codigo = Integer.parseInt(valor);
			UsuarioDAO fdao = new UsuarioDAO();	
			
			usuario = fdao.buscarPorCodigo(codigo);
				 
			 }
			 else
				 {
				usuario = new Usuario();
				
			 }
			 
		
		 } catch (RuntimeException e) {
		 JSFUtil.adicionarMensagemErro(e.getMessage());
		 e.printStackTrace();
		 }
		
		 } 
	 
	 public void editar(){
		 try {
		 UsuarioDAO fdao = new UsuarioDAO();
		 fdao.merge(usuario);
		
		
		 JSFUtil.adicionarMensagemSucesso("Usuario editado com sucesso!");
		
		 } catch (RuntimeException e) {
		 JSFUtil.adicionarMensagemErro(e.getMessage());
		 e.printStackTrace();
		 }
		 }
	
	
	
}
