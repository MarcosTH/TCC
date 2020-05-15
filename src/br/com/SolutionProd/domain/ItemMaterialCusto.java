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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "itens_material_custo")
@NamedQueries({
@NamedQuery(name = "ItemMaterialCusto.listar", query = "SELECT item FROM ItemMaterialCusto item" ),
@NamedQuery(name = "ItemMaterialCusto.listarPorCusto", query = "SELECT item FROM ItemMaterialCusto item WHERE item.custo = :id" ),
@NamedQuery(name = "ItemMaterialCusto.buscarPorCodigo", query = "SELECT item FROM ItemMaterialCusto item WHERE item.id = :id" ) })
public class ItemMaterialCusto {
		
	@Override
	public String toString() {
		return "ItemMaterialCusto [id=" + id + ", custo=" + custo + ", material=" + material + ", quantidade="
				+ quantidade + "]";
	}

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name="id_item")
	private int id;
		
	@NotNull(message = "Insira um custo")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="custo_id", referencedColumnName="id_custo", 
	nullable=false)
	private Custo custo;
	
	@NotNull(message = "Insira um material")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="material_id", referencedColumnName="id_material", 
	nullable=false)
	private Material material;
	
	@NotNull(message = "Insira a quantidade ")
	@Min(value = 0 , message = "O valor não pode ser negativo")
	@Column(name="quantidade_material",  nullable=false )
	private Integer quantidade;
	
	
	@DecimalMin(value = "0.00", message = "O valor não pode ser menor que 0")
	@Column(name="preco_unitario_material", scale=2, precision=7 )
	private BigDecimal valorParcial;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Custo getCusto() {
		return custo;
	}

	public void setCusto(Custo custo) {
		this.custo = custo;
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

	public BigDecimal getValorParcial() {
		return valorParcial;
	}

	public void setValorParcial(BigDecimal valorParcial) {
		this.valorParcial = valorParcial;
	}
	
	
	
	
	
	
	
	
	
	

}
