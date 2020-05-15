package br.com.SolutionProd.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "Fitas")
@NamedQueries({
@NamedQuery(name = "Fita.listar", query = "SELECT fita FROM Fita fita" ),
@NamedQuery(name = "Fita.buscarPorCodigo", query = "SELECT fita FROM Fita fita WHERE fita.id = :id" )

})
public class Fita {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name="id_fita")
	private int id;
	
	
	@NotEmpty(message = "Insira o nome da fita")
	@Column(name="nome_fita", length=50, nullable=false, unique = true )
	private String nome;

	@DecimalMin(value = "0.00", message = "O valor não pode ser menor que 0")
	@Column(name="preco_fita_metro", scale=2, precision=7 )
	private BigDecimal Preco_metro;

	@Override
	public String toString() {
		return "Fita [id=" + id + ", nome=" + nome + ", Preco_metro=" + Preco_metro + "]";
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

	public BigDecimal getPreco_metro() {
		return Preco_metro;
	}

	public void setPreco_metro(BigDecimal preco_metro) {
		Preco_metro = preco_metro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Preco_metro == null) ? 0 : Preco_metro.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Fita other = (Fita) obj;
		if (Preco_metro == null) {
			if (other.Preco_metro != null)
				return false;
		} else if (!Preco_metro.equals(other.Preco_metro))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	
	
	
	
}
