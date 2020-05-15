package br.com.SolutionProd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import jdk.nashorn.internal.ir.annotations.Immutable;

@Entity
@Immutable
@Table(name = "maiorespedidos")

@NamedQueries({
@NamedQuery(name = "MaioresPedidos.listar", query = "SELECT maiores FROM MaioresPedidos maiores" )

})
public class MaioresPedidos {

	
	@Id
	@Column(name="nome")
	private String  nome;
	@Column(name="soma")
	private int soma;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getSoma() {
		return soma;
	}
	public void setSoma(int soma) {
		this.soma = soma;
	}
	@Override
	public String toString() {
		return "MaioresPedidos [nome=" + nome + ", soma=" + soma + "]";
	}
	
	
	
}
