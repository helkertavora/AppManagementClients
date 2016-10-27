package br.com.unifor.AppManagementClients.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "municipio")
public class Municipio extends AbstractModel<Integer> {

	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name = "seq", allocationSize = 1, sequenceName = "seq_municipio")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@Column(name = "codigo_municipio")
	@Id
	private Integer id;
	
	@Column(name = "nome" , nullable = false, length = 80)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "codigo_uf")
	private UF uf;

	@Column(name = "populacao")
	private Long populacao;
	
	public Municipio() { 

	} 

	public Municipio(Integer id, String nome) { 
		this.id = id; 
		this.nome = nome; 
	} 

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	public Long getPopulacao() {
		return populacao;
	}

	public void setPopulacao(Long populacao) {
		this.populacao = populacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
