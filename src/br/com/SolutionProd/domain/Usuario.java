package br.com.SolutionProd.domain;

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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Usuarios")
@NamedQueries({
@NamedQuery(name = "Usuario.listar", query = "SELECT usuario FROM Usuario usuario" ),
@NamedQuery(name = "Usuario.buscarPorCodigo", query = "SELECT usuario FROM Usuario usuario WHERE usuario.id = :id" )

})
public class Usuario {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name="id_Usuario")
	private int id;


	@Column(length = 32, nullable = false)
	private String senha;
	
	@Transient
	private String senhaSemCriptografia;
	
	
	@Column()
	private Boolean ativo;
	
	@Column(nullable = false)
	private Character tipo;
	
	
	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public String getSenhaSemCriptografia() {
		return senhaSemCriptografia;
	}

	public void setSenhaSemCriptografia(String senhaSemCriptografia) {
		this.senhaSemCriptografia = senhaSemCriptografia;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}


	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="funcionario_id", referencedColumnName="id_funcionario", 
	nullable=false)
	private Funcionario funcionario;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", senha=" + senha + ", senhaSemCriptografia=" + senhaSemCriptografia + ", ativo="
				+ ativo + ", tipo=" + tipo + ", funcionario=" + funcionario + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result + ((funcionario == null) ? 0 : funcionario.hashCode());
		result = prime * result + id;
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((senhaSemCriptografia == null) ? 0 : senhaSemCriptografia.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Usuario other = (Usuario) obj;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (funcionario == null) {
			if (other.funcionario != null)
				return false;
		} else if (!funcionario.equals(other.funcionario))
			return false;
		if (id != other.id)
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (senhaSemCriptografia == null) {
			if (other.senhaSemCriptografia != null)
				return false;
		} else if (!senhaSemCriptografia.equals(other.senhaSemCriptografia))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}







	
}
