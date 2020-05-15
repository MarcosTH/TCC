package br.com.SolutionProd.domain;


import java.math.BigDecimal;

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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;



@Entity
@Table(name = "Materiais")
@NamedQueries({
@NamedQuery(name = "Material.listarMaiores", query = "SELECT material FROM Material material WHERE material.quantidade >= 2000   " ),
@NamedQuery(name = "Material.listar", query = "SELECT material FROM Material material" ),
@NamedQuery(name = "Material.listarFaltas", query = "SELECT material FROM Material material WHERE material.quantidade <= material.estoqueMinimo   " ),
@NamedQuery(name = "Material.buscarPorId", query = "SELECT material FROM Material material WHERE material.id = :id" ),
@NamedQuery(name = "Material.buscarPorCodigo", query = "SELECT material FROM Material material WHERE material.codigo = :codigo" )
})
public class Material {

	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name="id_material")
	private int id;
	
	@NotEmpty(message = "Insira o nome")
	@Column(name="nome_material", length=50, nullable=false, unique = true )
	private String nome;
	@NotEmpty(message = "Insira o codigo")
	@Column(name="codigo_material", length=50, nullable=false, unique = true )
	private String codigo;
	
	@NotNull(message = "Insira o Preço unitario")
	@DecimalMin(value = "0.00", message = "O valor não pode ser menor que 0")
	@Column(name="preco_unitario_material", nullable=false, scale=2, precision=7 )
	private BigDecimal preco;
	
	@NotNull(message = "Insira o estoque ")
	@Min(value = 0 , message = "O valor não pode ser negativo")
	@Column(name="estoque_material",  nullable=false )
	private Integer quantidade;
	
	@NotNull(message = "Insira o estoque minimo ")
	@Min(value = 0 , message = "O valor não pode ser zero")
	@Column(name="estoque_minimo_material",  nullable=false )
	private Integer estoqueMinimo;
	
	@NotNull(message = "Insira um Fornecedor")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="fornecedor_id", referencedColumnName="id_fornecedor", 
	nullable=false)
	private Fornecedor fornecedor;
	
	

	
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Material [id=" + id + ", nome=" + nome + ", codigo=" + codigo + ", preco=" + preco + ", quantidade="
				+ quantidade + ", estoqueMinimo=" + estoqueMinimo + ", fornecedor=" + fornecedor + "]";
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setEstoqueMinimo(Integer estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((estoqueMinimo == null) ? 0 : estoqueMinimo.hashCode());
		result = prime * result + ((fornecedor == null) ? 0 : fornecedor.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
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
		Material other = (Material) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (estoqueMinimo == null) {
			if (other.estoqueMinimo != null)
				return false;
		} else if (!estoqueMinimo.equals(other.estoqueMinimo))
			return false;
		if (fornecedor == null) {
			if (other.fornecedor != null)
				return false;
		} else if (!fornecedor.equals(other.fornecedor))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		return true;
	}
	
	
	
	
}
