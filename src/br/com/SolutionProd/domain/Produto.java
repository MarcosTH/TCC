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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "produtos")
@NamedQueries({
@NamedQuery(name = "Produto.listar", query = "SELECT produto FROM Produto produto" ),

@NamedQuery(name = "Produto.buscarPorCodigo", query = "SELECT produto FROM Produto produto WHERE produto.id = :id" ) })
public class Produto {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name="id_produto")
	private int id;
	

	@NotEmpty(message = "Insira o nome")
	@Column(name="nome_produto", length=50, nullable=false, unique = true )
	private String nome;
	
	
	@NotNull(message = "Insira o Preço de venda")
	@DecimalMin(value = "0.00", message = "O valor não pode ser menor que 0")
	@Column(name="preco_venda", nullable=false, scale=2, precision=7 )
	private BigDecimal precoVenda;
	

	
	
	@NotNull(message = "Insira uma fita")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="fita_id", referencedColumnName="id_fita", 
	nullable=false)
	private Fita fita;
	
	@NotNull(message = "Insira a metragem")
	@DecimalMin(value = "0.00", message = "O valor não pode ser menor que 0")
	@Column(name="metragem", nullable=false, scale=2, precision=7 )
	private BigDecimal metragem;
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fita == null) ? 0 : fita.hashCode());
		result = prime * result + id;
		result = prime * result + ((metragem == null) ? 0 : metragem.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((precoVenda == null) ? 0 : precoVenda.hashCode());
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
		Produto other = (Produto) obj;
		if (fita == null) {
			if (other.fita != null)
				return false;
		} else if (!fita.equals(other.fita))
			return false;
		if (id != other.id)
			return false;
		if (metragem == null) {
			if (other.metragem != null)
				return false;
		} else if (!metragem.equals(other.metragem))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (precoVenda == null) {
			if (other.precoVenda != null)
				return false;
		} else if (!precoVenda.equals(other.precoVenda))
			return false;
		return true;
	}


	public Fita getFita() {
		return fita;
	}


	public void setFita(Fita fita) {
		this.fita = fita;
	}


	public BigDecimal getMetragem() {
		return metragem;
	}


	public void setMetragem(BigDecimal metragem) {
		this.metragem = metragem;
	}


	public int getId() {
		return id;
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


	

	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}


	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}


	


public Produto() {}


	public Produto(int id, String nome,  BigDecimal precoVenda, BigDecimal custo) {
		super();
		this.id = id;
		this.nome = nome;
		
		this.precoVenda = precoVenda;
		
	}


	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", precoVenda=" + precoVenda + ", fita=" + fita + ", metragem="
				+ metragem + "]";
	}




	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
