package br.com.SolutionProd.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "custos")
@NamedQueries({
@NamedQuery(name = "Custo.listar", query = "SELECT custo FROM Custo custo" ),
@NamedQuery(name = "Custo.listarMargem", query = "SELECT custo FROM Custo custo ORDER by custo.margem" ),
@NamedQuery(name = "Custo.buscarPorCodigo", query = "SELECT custo FROM Custo custo WHERE custo.id = :id" ),
@NamedQuery(name = "Custo.buscarPorProduto", query = "SELECT custo FROM Custo custo WHERE custo.produto = :id" )

})
public class Custo {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name="id_custo")
	private int id;
	
	@Temporal (value=TemporalType.DATE)
	@Column(name="data_custo",  nullable=false )
	private Date data;
	

	@NotNull(message = "Insira um produto")
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="produto_id", referencedColumnName="id_produto", 
	nullable=false)
	private Produto produto;


	
	@Column(name="custo", scale=2, precision=7 )
	private BigDecimal custo;
	
	
	@Column(name="margem", scale=2, precision=7 )
	private BigDecimal margem;

	




	@Override
	public String toString() {
		return "Custo [id=" + id + ", data=" + data + ", produto=" + produto + ", custo=" + custo + ", margem=" + margem
				+ "]";
	}


	public BigDecimal getMargem() {
		return margem;
	}


	public void setMargem(BigDecimal margem) {
		this.margem = margem;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public BigDecimal getCusto() {
		return custo;
	}


	public void setCusto(BigDecimal custo) {
		this.custo = custo;
	}
	
	
	
	
	
	
	
	
	
	
	
}
