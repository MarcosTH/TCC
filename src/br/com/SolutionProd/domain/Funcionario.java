package br.com.SolutionProd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "Funcionarios")
@NamedQueries({
@NamedQuery(name = "Funcionario.listar", query = "SELECT funcionario FROM Funcionario funcionario" ),
@NamedQuery(name = "Funcionario.buscarPorCodigo", query = "SELECT funcionario FROM Funcionario funcionario WHERE funcionario.id = :id" )})

public class Funcionario {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name="id_funcionario")
	private int id;
	
	@NotEmpty(message = "Insira a nome")
	@Column(name="nome_funcionario", length=50, nullable=false, unique = true )
	private String nome;
	
	
	@NotEmpty(message = "Insira a funcao")
	@Column(name="funcao_funcionario", length=50, nullable=false )
	private String funcao;
	
	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
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

	



	

	

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", funcao=" + funcao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((funcao == null) ? 0 : funcao.hashCode());
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
		Funcionario other = (Funcionario) obj;
		if (funcao == null) {
			if (other.funcao != null)
				return false;
		} else if (!funcao.equals(other.funcao))
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
