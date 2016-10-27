package br.com.unifor.AppManagementClients.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "cliente")
public class Cliente extends AbstractModel<Integer> {

	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name = "seq", allocationSize = 1, sequenceName = "seq_cliente")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@Column(name = "codigo_cliente")
	@Id
	private Integer id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "endereco")
	private String endereco;
		
	@Column(name = "observacao")
	private String observacao;
	
	@Column(name = "data_nascimento")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Column(name = "estado_civil")
	private Integer estadoCivil;
   
	@ManyToOne
	@JoinColumn(name = "codigo_municipio")
	private Municipio municipio;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "cliente_lazer", 
	joinColumns = @JoinColumn(name = "codigo_cliente"), 
	inverseJoinColumns = @JoinColumn(name = "codigo_lazer"))
	private List <Lazer> listaLazer;  	
	
	
	@Column(name = "dlatitude")
	private Double dlatitude;
	
	@Column(name = "dlongitude")
	private Double dlongitude;
	
	@Column(name = "nomeArquivo")
	private String nomeArquivo;
	
	@ManyToOne
	@Transient
	private UF uf;
	
	public Cliente() { 

	} 

	public Cliente(Integer id, String nome) { 
		this.id = id; 
		this.nome = nome; 
	} 

	
	public List<Lazer> getListaLazer() {
		return listaLazer;
	}

	public void setListaLazer(List<Lazer> listaLazer) {
		this.listaLazer = listaLazer;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Integer getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(Integer estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}	
	
	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public String getDscEstadoCivil() {
		String retorno = null;
		switch (this.estadoCivil) {
		case 2:
			retorno = "Casado";
			break;
		case 3:
			retorno = "Divorciado";
			break;
		default:
			retorno = "Solteiro";
			break;
		}
		return retorno;
	}

	
	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}
	
	public Float getLatitude() {
		return Float.parseFloat(dlatitude + "f");
	}
	
	public Double getDlatitude() {
		return dlatitude;
	}

	public void setDlatitude(Double dlatitude) {
		this.dlatitude = dlatitude;
	}

	public Float getLongitude() {
		return Float.parseFloat(dlongitude + "f");
	}

	public Double getDlongitude() {
		return dlongitude;
	}

	public void setDlongitude(Double dlongitude) {
		this.dlongitude = dlongitude;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	} 
	
	
}
