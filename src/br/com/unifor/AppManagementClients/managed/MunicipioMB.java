package br.com.unifor.AppManagementClients.managed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.unifor.AppManagementClients.dao.MunicipioDao;
import br.com.unifor.AppManagementClients.model.Municipio;


@SessionScoped
@ManagedBean(name="municipioMB")
public class MunicipioMB extends AbstractMB {
	
	private Municipio municipio;
	private List<Municipio> listagemResultadoBusca;
	protected MunicipioDao municipioDao = new MunicipioDao();
	// Inicializando
	
	public MunicipioMB() {
		municipio = new Municipio();
		listagemResultadoBusca = new ArrayList<Municipio>();
		total = 0;
	}
    
	public String init() {
		try {
			municipio = new Municipio();
			listagemResultadoBusca = new ArrayList<Municipio>();
			carregarListaUfSelect();
			total = 0;
			this.consultar();
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage());
		}
		return "menuMunicipio";
	}		
    
	// Opcional ver validacao de tela
	
	protected boolean validarMunicipio(Municipio municipio) throws Exception {
		boolean retorno = true;
		if (municipio != null) {
			if (municipio.getNome().equals("")) {
				retorno = false;
				addErrorMessage(getMessage("nomeCampoObrigatorio"));
			}
			if (municipio.getPopulacao() < 1) {
				retorno = false;
				addErrorMessage(getMessage("populacaoCampoObrigatorio"));
			}
			if (municipio.getUf() == null && municipio.getId() < 0) {
				retorno = false;
				addErrorMessage(getMessage("ufCampoObrigatorio"));
			}			
		}
		return retorno;
	}
    
	//Pessistencia
	
	public String salvar() {
		try {
			if (validarMunicipio(municipio)){// Opcional ver validacao de tela
				getMunicipioDao().incluir(municipio);
				municipio = new Municipio();
				this.consultar();
			    addInfoMessage("Opera��o efetuada com sucesso");
			    return "menuMunicipio";
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage());
			return null;
		}
	}

	public String atualizar() {
		try {
			if (validarMunicipio(municipio)){
				getMunicipioDao().atualizar(municipio);
				municipio = new Municipio();
				this.consultar();
				addInfoMessage("Opera��o efetuada com sucesso");
				return "menuMunicipio";
			} else {
				return null;
			}			
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage());
			return "*";
		}
	}

	public String excluir() {
		try {
			getMunicipioDao().excluir(municipio.getId());
			this.consultar();			
			municipio = new Municipio();
			addInfoMessage("Opera��o efetuada com sucesso");			
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage());
		}
		return null;
	}
	
	public String consultar() {
		listagemResultadoBusca = getMunicipioDao().consultaHql(municipio);
		total = listagemResultadoBusca.size();
		if (total == 0) {
			addInfoMessage("Nenhum registro encontrado");
		}		
		municipio = new Municipio();
		return null;		
	}
	
	public void relatorio() {
		HashMap paramRel = new HashMap();
		List<Municipio> listaRel = getMunicipioDao().consulta(municipio);	
		String nomeRelatorio = "relMunicipio";		
		gerarRelatorio(nomeRelatorio, paramRel,listaRel);
	}
	
	public void relatorioGrafico() {
		HashMap paramRel = new HashMap();
		List<Municipio> listaRel = getMunicipioDao().consulta(municipio);		
		String nomeRelatorio = nomeRelatorio = "relMunicipioGrafico";		
		gerarRelatorio(nomeRelatorio, paramRel,listaRel);		
	}
	
	public void relatorioCrossTab() {
		HashMap paramRel = new HashMap();
		List<Municipio> listaRel = getMunicipioDao().consulta(municipio);	
		String nomeRelatorio = "relMunicipioCT";		
		gerarRelatorio(nomeRelatorio, paramRel,listaRel);
	}
	
	
	// Navega��o
	
	public String incluir() {
		municipio = new Municipio();
		return "novoMunicipio";
	}
	
	public String editar() {
		return "editarMunicipio";
	}


	public String cancelar() {
		return "menuMunicipio";
	}

		
	
	// gets e setters	
	
	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public List<Municipio> getListagemResultadoBusca() {
		return listagemResultadoBusca;
	}

	public void setListagemResultadoBusca(List<Municipio> listagemResultadoBusca) {
		this.listagemResultadoBusca = listagemResultadoBusca;
	}

	public MunicipioDao getMunicipioDao() {
		return municipioDao;
	}

	public void setMunicipioDao(MunicipioDao municipioDao) {
		this.municipioDao = municipioDao;
	}
	
	

}
