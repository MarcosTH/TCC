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

@Entity
@Table(name = "Item_nf")
@NamedQueries({
@NamedQuery(name = "ItemNF.listar", query = "SELECT itemnf FROM ItemNF itemnf" ),
@NamedQuery(name = "ItemNF.buscarPorCodigo", query = "SELECT itemnf FROM ItemNF itemnf WHERE itemnf.id = :id" )

})
public class ItemNF {
	
	public ItemNF() {}
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name="id_item_nf")
	private int id;

	@NotNull(message = "Insira uma NF")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="nf_id", referencedColumnName="id_NF", 
	nullable=false)
	private NF nf;
	
	@NotNull(message = "Insira um Material")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="material_id", referencedColumnName="id_material", 
	nullable=false)
	private Material material;
	
	@NotNull(message = "Insira a quantidade ")
	@Min(value = 0 , message = "O valor não pode ser negativo")
	@Column(name="quantidade",  nullable=false )
	private Integer quantidade;
	
	@DecimalMin(value = "0.00", message = "O valor não pode ser menor que 0")
	@Column(name="preco_unitario_material", scale=2, precision=7 )
	private BigDecimal valorParcial;
	

	public BigDecimal getValorParcial() {
		return valorParcial;
	}

	public void setValorParcial(BigDecimal valorParcial) {
		this.valorParcial = valorParcial;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public NF getNf() {
		return nf;
	}

	public void setNf(NF nf) {
		this.nf = nf;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	

	@Override
	public String toString() {
		return "ItemNF [id=" + id + ", nf=" + nf + ", material=" + material + ", quantidade=" + quantidade
				+ ", valorParcial=" + valorParcial + "]";
	}

	
	
}
