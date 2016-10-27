package br.com.unifor.AppManagementClients.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "uf")
public class UF extends AbstractModel<Integer> {

	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name = "seq", allocationSize = 1, sequenceName = "seq_uf")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@Column(name = "codigo_uf")	
	@Id
	private Integer id;
	
	@Column(name = "nome")
	private String nome;

	@Column(name = "sigla",nullable = false, length = 2)
	private String sigla;
	
	public UF() { 

	} 

	public UF(Integer id, String nome) { 
		this.id = id; 
		this.nome = nome; 
	} 


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}
