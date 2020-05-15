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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "NF")
@NamedQueries({
@NamedQuery(name = "NF.listar", query = "SELECT nf FROM NF nf " ),
@NamedQuery(name = "NF.listarMaiores", query = "SELECT nf  FROM NF nf INNER JOIN nf.fornecedor f  group by f.nome " ),
@NamedQuery(name = "NF.buscarPorCodigo", query = "SELECT nf FROM NF nf WHERE nf.id = :id" ),
@NamedQuery(name = "NF.buscarPorFornecedor", query = "SELECT nf FROM NF nf WHERE fornecedor.id = :id" )
})

public class NF implements Serializable{
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name="id_NF")
	private int id;
	
	@Temporal (value=TemporalType.DATE)
	@Column(name="data_NF",  nullable=false )
	private Date data;
	
	@NotNull(message = "Insira um Fornecedor")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="fornecedor_id", referencedColumnName="id_fornecedor", 
	nullable=false)
	private Fornecedor fornecedor;
	
	
	@DecimalMin(value = "0.00", message = "O valor não pode ser menor que 0")
	@Column(name="valor_total", scale=2, precision=7 )
	private BigDecimal total;


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


	public Fornecedor getFornecedor() {
		return fornecedor;
	}


	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}


	public BigDecimal getTotal() {
		return total;
	}


	public void setTotal(BigDecimal total) {
		this.total = total;
	}


	@Override
	public String toString() {
		return "NF [id=" + id + ", data=" + data + ", fornecedor=" + fornecedor + ", total=" + total + "]";
	}



	

}
