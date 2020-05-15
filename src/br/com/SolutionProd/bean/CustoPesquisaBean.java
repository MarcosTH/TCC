package br.com.SolutionProd.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.SolutionProd.DAO.CustoDAO;
import br.com.SolutionProd.DAO.ItemMaterialCustoDAO;
import br.com.SolutionProd.DAO.MaterialDAO;
import br.com.SolutionProd.DAO.ProdutoDAO;
import br.com.SolutionProd.domain.Custo;
import br.com.SolutionProd.domain.ItemMaterialCusto;
import br.com.SolutionProd.domain.Material;
import br.com.SolutionProd.domain.Produto;
import br.com.SolutionProd.util.JSFUtil;


@ManagedBean(name = "MBCustoPesquisa")
@ViewScoped
public class CustoPesquisaBean {
	
	 
	private Material material;

	 private ArrayList<ItemMaterialCusto>itens;
	 private ArrayList<ItemMaterialCusto>itensFiltrados;
	 private ArrayList<Material>materiais;
	 private ArrayList<Material>materiaisFiltrados; 
	 private Custo custo;
	 private Produto produto;
	 private ArrayList<Custo> custos;
	 private ArrayList<Custo> custosFiltrados;	
	 private ArrayList<Produto>produtos;
	 private ArrayList<Produto>produtosFiltrados;
	 private Integer codigo;
	 
 
	
	public ArrayList<Custo> getCustos() {
		return custos;
	}

	public void setCustos(ArrayList<Custo> custos) {
		this.custos = custos;
	}

	public ArrayList<Custo> getCustosFiltrados() {
		return custosFiltrados;
	}

	public void setCustosFiltrados(ArrayList<Custo> custosFiltrados) {
		this.custosFiltrados = custosFiltrados;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public ArrayList<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public void setProdutosFiltrados(ArrayList<Produto> produtosFiltrados) {
		this.produtosFiltrados = produtosFiltrados;
	}

	

	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}

	

	public Produto getProduto() {
		if(produto == null) {
			produto = new Produto();
			
			
		}
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Custo getCusto() {
		if(custo == null) {
			custo = new Custo();
			
			
		}
		return custo;
	}

	public void setCusto(Custo custo) {
		this.custo = custo;
	}

	public ArrayList<Material> getMateriais() {
		return materiais;
	}

	public void setMateriais(ArrayList<Material> matriais) {
		this.materiais = matriais;
	}

	public ArrayList<Material> getMateriaisFiltrados() {
		return materiaisFiltrados;
	}

	public void setMateriaisFiltrados(ArrayList<Material> materiaisFiltrados) {
		this.materiaisFiltrados = materiaisFiltrados;
	}

	public Material getMaterial() {
		if(material == null) {
			material = new Material();
			
		}
		
		return material;
	}

	
	public void setMaterial(Material material) {
		this.material = material;
	}

	 public ArrayList<ItemMaterialCusto> getItens() {
		 if(itens == null) {
			 itens = new ArrayList<>();
			 
		 }
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

	
	 public void carregarMateriais(){

		 try {
		     
			MaterialDAO dao = new MaterialDAO();
			materiais = (ArrayList<Material>) dao.listar();
						
			 
		 } catch (RuntimeException e) {
		 JSFUtil.adicionarMensagemErro("ex.getMessage()");
		 e.printStackTrace();
		 }
		
		 } 
	 
	 
	 
	 public void carregarCustos() {
		
		 
		 try {
			 CustoDAO custodao = new CustoDAO();
			 custo =  custodao.buscarPorCodigo(codigo);
			 
			 
			 
			 
				
								 
			 } catch (RuntimeException e) {
			 JSFUtil.adicionarMensagemErro("ex.getMessage()");
			 e.printStackTrace();
			 }
		 		 
		
	 }
	 
	
	
	
	
	 

	
	 
	 public void carregarProdutos() {
		 
		 ProdutoDAO pdao = new ProdutoDAO();
		 produtos = (ArrayList<Produto>) pdao.listar();
	 }
	 	 
	 
public void pegarProduto() {
	
	try {
	
	 String valor = JSFUtil.getParam("cuscod");

	 if(valor != null){
		
		 codigo = Integer.parseInt(valor);
	CustoDAO pdao = new CustoDAO();	
	
	custo = pdao.buscarPorCodigo(codigo);
	
	
	 }
	 else
		 {
		custo = new Custo();
		
	 }
	 } catch (RuntimeException e) {
		 JSFUtil.adicionarMensagemErro(e.getMessage());
		 e.printStackTrace();
		 }
	
}

}
