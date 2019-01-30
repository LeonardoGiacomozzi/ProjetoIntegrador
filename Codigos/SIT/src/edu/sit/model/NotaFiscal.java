package edu.sit.model;

import java.io.Serializable;
import java.util.Calendar;

public class NotaFiscal implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Venda venda;
	private Integer vendaid;
	private Calendar dataEmissao;
	private Double total = 0.0;

	public Integer getVendaid() {
		return vendaid;
	}

	public void setVendaid(Integer vendaid) {
		this.vendaid = vendaid;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Calendar getDataEmissao() {
		return dataEmissao;
	}

	public NotaFiscal(Venda venda) {
		setVenda(venda);
		this.dataEmissao = Calendar.getInstance();
	}
	
	public NotaFiscal(Integer id, Calendar dataEmissao, Double total, Integer vendaid) {
		setId(vendaid);
		this.dataEmissao = dataEmissao;
		setTotal(total);
		setVendaid(vendaid);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		NotaFiscal other = (NotaFiscal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	@Override
	public String toString() {
		return "\nID: \t\t" + getId() + "\nData Emissão: \t" + getDataEmissao() + "\nTotal: \t\t" + getTotal() + getVenda();
				
	}

}
