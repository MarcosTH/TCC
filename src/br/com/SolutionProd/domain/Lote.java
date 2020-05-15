package br.com.SolutionProd.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "lote")
@NamedQueries({
@NamedQuery(name = "Lote.listar", query = "SELECT lote FROM Lote lote" ),
@NamedQuery(name = "Lote.buscarPorFuncionario", query = "SELECT lote FROM Lote lote WHERE lote.funcionario = :id" ),
@NamedQuery(name = "Lote.buscarPorCodigo", query = "SELECT lote FROM Lote lote WHERE funcionario.id = :id" )
})


public class Lote implements Serializable {
	
	@Override
	public String toString() {
		return "Lote [id=" + id + ", data=" + data + ", produto=" + produto + ", quantidade=" + quantidade
				+ ", custoTotal=" + custoTotal + ", margemTotal=" + margemTotal + ", funcionario=" + funcionario + "]";
	}

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name="id_lote")
	private int id;
	
	@Temporal (value=TemporalType.DATE)
	@Column(name="data_lote",  nullable=false )
	private Date data;
	
	@NotNull(message = "Insira um produto")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="produto_id", referencedColumnName="id_produto", 
	nullable=false)
	private Produto produto;
	
	@NotNull(message = "Insira a quantidade")
	@Min(value = 0, message = "O valor não pode ser menor que 0")
	@Column(name = "quantidade", nullable = false)
	private Integer quantidade;
	
	@Transient
	@DecimalMin(value = "0.00", message = "O valor não pode ser menor que 0")
	@Column(name="custo_total", scale=2, precision=7 )
	private BigDecimal custoTotal;
	
	@Transient
	@DecimalMin(value = "0.00", message = "O valor não pode ser menor que 0")
	@Column(name="margem_total", scale=2, precision=7 )
	private BigDecimal margemTotal;
	
	@NotNull(message = "Insira um funcionario")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="funcionario_id", referencedColumnName="id_funcionario", 
	nullable=false)
	private Funcionario funcionario;

	
	
	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
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

	public BigDecimal getCustoTotal() {
		return custoTotal;
	}

	public void setCustoTotal(BigDecimal custoTotal) {
		this.custoTotal = custoTotal;
	}

	public BigDecimal getMargemTotal() {
		return margemTotal;
	}

	public void setMargemTotal(BigDecimal margemTotal) {
		this.margemTotal = margemTotal;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lote other = (Lote) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	
	
	

}
