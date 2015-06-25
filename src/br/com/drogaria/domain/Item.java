package br.com.drogaria.domain;

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

@Entity
@Table(name = "tbl_itens")
@NamedQueries({
	 @NamedQuery(name="Item.lista",query="Select  item from Item item"),
	 @NamedQuery(name="Item.buscaPorCodigo",query="Select item from Item item where codigo = :codigo")
	
})
public class Item {

	@Override
	public String toString() {
		return "Item [codigo=" + codigo + ", valor=" + valor + ", quantidade="
				+ quantidade + ", produto=" + produto + ", venda=" + venda
				+ "]";
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Item other = (Item) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ite_codigo")
	private Long codigo;

	@Column(name = "ite_valor_parcial", precision = 7, scale = 2, nullable = false)
	private BigDecimal valor;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	@Column(name = "ite_quantidade", nullable = false)
	private Integer quantidade;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tbl_produtos_pro_codigo", referencedColumnName = "pro_codigo", nullable = false)
	private Produto produto;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tbl_vendas_ven_codigo", referencedColumnName = "ven_codigo", nullable = false)
	private Venda venda;
}
