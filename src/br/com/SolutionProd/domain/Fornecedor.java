package br.com.SolutionProd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "Fornecedores")
@NamedQueries({
@NamedQuery(name = "Fornecedor.listar", query = "SELECT fornecedor FROM Fornecedor fornecedor" ),
@NamedQuery(name = "Fornecedor.buscarPorCodigo", query = "SELECT fornecedor FROM Fornecedor fornecedor WHERE fornecedor.id = :id" )

})
public class Fornecedor {
	
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
		Fornecedor other = (Fornecedor) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name="id_fornecedor")
	private int id;
	
	
	@NotEmpty(message = "Insira a nome")
	@Column(name="nome_fornecedor", length=50, nullable=false, unique = true )
	private String nome;
	
	@NotEmpty(message = "Insira o email")
	@Column(name="email_fornecedor", length=50, nullable=false )
	private String email;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Fornecedor [id=" + id + ", nome=" + nome + ", email=" + email + "]";
	}
	
	
	

}
