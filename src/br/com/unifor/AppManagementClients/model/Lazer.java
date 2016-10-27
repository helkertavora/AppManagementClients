package br.com.unifor.AppManagementClients.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "lazer")
public class Lazer extends AbstractModel<Integer> {

	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name = "seq", allocationSize = 1, sequenceName = "seq_lazer")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@Column(name = "codigo_lazer")
	@Id
	private Integer id;
	
	@Column(name = "descricao")
	private String descricao;

	public Lazer() { 

	} 

	public Lazer(Integer id, String descricao) { 
		this.id = id; 
		this.descricao = descricao; 
	} 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
	
}
  