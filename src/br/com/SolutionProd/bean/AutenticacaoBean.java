package br.com.SolutionProd.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.SolutionProd.DAO.CustoDAO;
import br.com.SolutionProd.DAO.FuncionarioDAO;
import br.com.SolutionProd.DAO.LoteDAO;
import br.com.SolutionProd.DAO.MaterialDAO;
import br.com.SolutionProd.DAO.NFDAO;
import br.com.SolutionProd.DAO.ProdutoDAO;
import br.com.SolutionProd.DAO.UsuarioDAO;
import br.com.SolutionProd.domain.Custo;
import br.com.SolutionProd.domain.Funcionario;
import br.com.SolutionProd.domain.Lote;
import br.com.SolutionProd.domain.Material;
import br.com.SolutionProd.domain.NF;
import br.com.SolutionProd.domain.Produto;
import br.com.SolutionProd.domain.Usuario;


@ManagedBean(name = "AutenticacaoMB")
@SessionScoped
public class AutenticacaoBean  {
	  
	private String qtdCusto;
	private String qtdProdutos;
	private String qtdMateriais;
	private String qtdGraficos;
	private String qtdLotes;
	private String qtdNotas;	
	private Custo custo;
	private List<Custo> custos;
	private Produto produto;
	private List<Produto> produtos;
	private Material material;
	private List<Material> materiais;
	private String qtdUsuarios;
	private String qtdFuncionarios;
	private String qtdRelatorios;
	private String qtdDevolvidos;
	
	
	
	public String getQtdUsuarios() {
		return qtdUsuarios;
	}

	public void setQtdUsuarios(String qtdUsuarios) {
		this.qtdUsuarios = qtdUsuarios;
	}

	public String getQtdFuncionarios() {
		return qtdFuncionarios;
	}

	public void setQtdFuncionarios(String qtdFuncionarios) {
		this.qtdFuncionarios = qtdFuncionarios;
	}

	public String getQtdRelatorios() {
		return qtdRelatorios;
	}

	public void setQtdRelatorios(String qtdRelatorios) {
		this.qtdRelatorios = qtdRelatorios;
	}

	public String getQtdDevolvidos() {
		return qtdDevolvidos;
	}

	public void setQtdDevolvidos(String qtdDevolvidos) {
		this.qtdDevolvidos = qtdDevolvidos;
	}

	private Usuario usuario;
	 private Usuario usuarioLogado;
	 
	 
	   
	 
	 public String getQtdLotes() {
		return qtdLotes;
	}

	public void setQtdLotes(String qtdLotes) {
		this.qtdLotes = qtdLotes;
	}

	public String getQtdNotas() {
		return qtdNotas;
	}

	public void setQtdNotas(String qtdNotas) {
		this.qtdNotas = qtdNotas;
	}

	public String getQtdCusto() {
		return qtdCusto;
	}

	public void setQtdCusto(String qtdCusto) {
		this.qtdCusto = qtdCusto;
	}

	public String getQtdProdutos() {
		return qtdProdutos;
	}

	public void setQtdProdutos(String qtdProdutos) {
		this.qtdProdutos = qtdProdutos;
	}

	public String getQtdMateriais() {
		return qtdMateriais;
	}

	public void setQtdMateriais(String qtdMateriais) {
		this.qtdMateriais = qtdMateriais;
	}

	public String getQtdGraficos() {
		return qtdGraficos;
	}

	public void setQtdGraficos(String qtdGraficos) {
		this.qtdGraficos = qtdGraficos;
	}

	public Custo getCusto() {
		return custo;
	}

	public void setCusto(Custo custo) {
		this.custo = custo;
	}

	public List<Custo> getCustos() {
		return custos;
	}

	public void setCustos(List<Custo> custos) {
		this.custos = custos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public List<Material> getMateriais() {
		return materiais;
	}

	public void setMateriais(List<Material> materiais) {
		this.materiais = materiais;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	 
	 public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	 
	 public Usuario getUsuario() {
		return usuario;
	}
	 
	 public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	 
	 @PostConstruct
	 public void iniciar(){
		 usuario = new Usuario();
		 usuario.setFuncionario(new Funcionario());
	 }
	 
	 
	 public void autenticar(){
		 try{
			 
			UsuarioDAO usuariodao = new UsuarioDAO();
			usuarioLogado = usuariodao.autenticar(usuario.getFuncionario().getNome(), usuario.getSenha());
			
			if(usuarioLogado == null){
				Messages.addGlobalError("Nome ou senha incorretos");
				return;
			}
			 
		 Faces.redirect("./pages/Principal.xhtml");
		 }catch(IOException erro){
			 erro.printStackTrace();
		 }
	 }
	 
	 public boolean temPermissoes(List<String> permissoes){		
			for(String permissao : permissoes){
				if(usuarioLogado.getTipo() == permissao.charAt(0)){
					return true;
				}
			}
			
			return false;
		}
	 
	 public String logout() {
	      HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	      sessao.invalidate();
	      return "Autenticacao.xhtml"; //AQUI EU PASSO O NOME DA MINHA TELA INICIAL.
	    }

	
	 public void contarProdutos() {
		int count = 0;
		 ProdutoDAO pdao = new ProdutoDAO();
		 for(Produto prod : pdao.listar()) {
			 count = count +1;
			 			 
		 }
		 this.setQtdProdutos(String.valueOf(count));
		 
	 }	 

	
	 public void contarMateriais() {
		int count = 0;
		 MaterialDAO mdao = new MaterialDAO();
		 for(Material mat : mdao.listar()) {
			 count = count +1;
			 			 
		 }
		 this.setQtdMateriais(String.valueOf(count));
		 
	 }
	 

	 public void contarCustos() {
		int count = 0;
		 CustoDAO cdao = new CustoDAO();
		 for(Custo cus : cdao.listar()) {
			 count = count +1;
			 			 
		 }
		 this.setQtdCusto(String.valueOf(count));
		 
	 }
	   
	 
	
	 public void contarNotas() {
		int count = 0;
		 NFDAO ndao = new NFDAO();
		 for(NF nf : ndao.listar()) {
			 count = count +1;
			 			 
		 }
		 this.setQtdNotas(String.valueOf(count));
		 
	 }
	 
	
	 public void contarLotes() {
		int count = 0;
		 LoteDAO ldao = new LoteDAO();
		 for(Lote lote : ldao.listar()) {
			 count = count +1;
			 			 
		 }
		 this.setQtdLotes(String.valueOf(count));
		 
	 }
	 
	 public void contarGraficos() {
			int count = 6;
			 
			 this.setQtdGraficos((String.valueOf(count)));
			 
		 }
	 public void contarUsuarios() {
			int count = 0;
			UsuarioDAO udao = new UsuarioDAO();
			for(Usuario usuario : udao.listar()) {
				 count = count +1;
				 			 
			 }
			 
			 this.setQtdUsuarios((String.valueOf(count)));
			 
		 }
	 public void contarFuncionarios() {
			int count = 0;
			FuncionarioDAO fdao = new FuncionarioDAO();
			for(Funcionario f : fdao.listar()) {
				 count = count +1;
				 			 
			 }
			 
			 this.setQtdFuncionarios((String.valueOf(count)));
			 
		 }
	 public void contarDevolvidos() {
			int count = 6;
			 
			 this.setQtdDevolvidos((String.valueOf(count)));
			 
		 }
	 public void contarRelatorios() {
			int count = 9;
			 
			 this.setQtdRelatorios((String.valueOf(count)));
			 
		 }
	
	 
}
