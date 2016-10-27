package br.com.unifor.AppManagementClients.managed;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DualListModel;

import br.com.unifor.AppManagementClients.dao.LazerDao;
import br.com.unifor.AppManagementClients.dao.MunicipioDao;
import br.com.unifor.AppManagementClients.model.Cliente;
import br.com.unifor.AppManagementClients.model.Lazer;
import br.com.unifor.AppManagementClients.model.Municipio;

@SessionScoped
@ManagedBean(name="clienteMB")
public class ClienteMB extends AbstractMB {
	
	
	private Cliente cliente;
	private List<Cliente> listagemResultadoBusca;
	protected List<Lazer> listaLazerSelect = new ArrayList<Lazer>();
	protected List<Municipio> listaMunicipioSelect = new ArrayList<Municipio>();
	protected MunicipioDao municipioDao = new MunicipioDao();
	protected LazerDao lazerDao = new LazerDao();
	private DualListModel<Lazer> lazeres; 
	protected List<Lazer> listaLazerSel = new ArrayList<Lazer>();

	
	public ClienteMB() {
		cliente = new Cliente();
		listagemResultadoBusca = new ArrayList<Cliente>();
		total = 0;		
	}
 
 	public String init() {
 		cliente = new Cliente();
		listagemResultadoBusca = new ArrayList<Cliente>();
		total = 0;
		carregarListaUfSelect();		
		carregarListaMunicipioSelect();
		return "sucesso";
	}
	
 	public void limpar() {
		cliente = new Cliente();
		listagemResultadoBusca = new ArrayList<Cliente>();
		total = 0;		
	}
 	
 	public void listener(FileUploadEvent event)  throws Exception{
 		cliente.setNomeArquivo(this.carregarArquivo(event));
	}
 	
    private String carregarArquivo(FileUploadEvent event) // metodo chamado quando o arquivo acaba de carregar no serverSide  
            throws FileNotFoundException, IOException {  
    	String arquivo = "";  
        arquivo = event.getFile().getFileName(); // pego o nome do arquivo  
        String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\arquivo\\"+arquivo ); // diretorio o qual vou salvar o arquivo do upload, equivale ao nome completamente qualificado  
        byte[] conteudo = event.getFile().getContents();  // daqui pra baixo � somente opera��es de IO.  
        FileOutputStream fos = new FileOutputStream(caminho);  
        fos.write(conteudo);  
        fos.close();
        return arquivo;
    }  
	
 	
	//Pessistencia
	public String salvar() {
		try {
			    cliente.setListaLazer(lazeres.getTarget());
				getClienteDao().incluir(cliente);	
				cliente = new Cliente();
				this.consultar();	
				addInfoMessage("Opera��o efetuada com sucesso.");			    
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage());
		}
		return "sucesso";
	}

	public String atualizar() {
		try {		
			cliente.setListaLazer(lazeres.getTarget());
			getClienteDao().atualizar(cliente);	
			cliente = new Cliente();
			this.consultar();	
			addInfoMessage("Opera��o efetuada com sucesso.");
			return "sucesso";
						
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage());
			return null;
		}
	} 

	public String  excluir() {
		try {
			getClienteDao().excluir(cliente.getId());
			this.consultar();		
			listagemResultadoBusca.remove(cliente);
			cliente = new Cliente();
			addInfoMessage("Opera��o efetuada com sucesso.");			
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage());
		}
		return null;
	}
	
	public String consultar() {
		listagemResultadoBusca = getClienteDao().consultaHql(cliente);
		total = listagemResultadoBusca.size();
		if (total == 0) {
			addInfoMessage("Nenhum Registro foi localizado.");
		}		
		return null;
	}	
	
	protected void carregarListaMunicipioSelect() {
		try {
			listaMunicipioSelect = municipioDao.todos("nome");
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage());
		}
	}
	
	public List<Municipio> getListaMunicipioUFSelect() {
		List<Municipio> retorno = new ArrayList<Municipio>();
		if ((cliente.getUf() != null)&& (cliente.getUf().getId()> 0)) {
			    Municipio municipioAux = new Municipio();
				municipioAux.setUf(cliente.getUf());
				retorno = getMunicipioDao().consulta(municipioAux); 
		}
		return retorno;
	}


	protected void carregarListaLazerSelect() {
		try {
			listaLazerSelect = new ArrayList<Lazer>();
			listaLazerSelect = lazerDao.todos("descricao");			
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage());
		}
	}

	
	public String incluir() {
		cliente = new Cliente();
		lazeres = new DualListModel<Lazer>(listaLazerSelect,listaLazerSel);
		return "sucesso";
	}	
   
	public String editar() {
		listaLazerSel = cliente.getListaLazer();
		lazeres = new DualListModel<Lazer>(listaLazerSelect,listaLazerSel); 
		return "/paginas/cliente/ClienteEditar.xhtml?faces-redirect=true";
	}

	public String cancelar() {
		return "/paginas/cliente/Cliente.xhtml?faces-redirect=true";
	}
	
	public void relatorio() {		
		listagemResultadoBusca = getClienteDao().consulta(cliente);		
		HashMap paramRel = new HashMap();
		String nomeRelatorio = "relCliMap";		
		gerarRelatorio(nomeRelatorio, paramRel,listagemResultadoBusca);		
	}
    
		
	// gets e setters
		
	public List<Cliente> getListagemResultadoBusca() {
		return listagemResultadoBusca;
	}

	
	public Cliente getcliente() {
		return cliente;
	}

	public void setcliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void setListagemResultadoBusca(List<Cliente> listagemResultadoBusca) {
		this.listagemResultadoBusca = listagemResultadoBusca;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Lazer> getListaLazerSelect() {
		return listaLazerSelect;
	}

	public void setListaLazerSelect(List<Lazer> listaLazerSelect) {
		this.listaLazerSelect = listaLazerSelect;
	}

	public MunicipioDao getMunicipioDao() {
		return municipioDao;
	}

	public void setMunicipioDao(MunicipioDao municipioDao) {
		this.municipioDao = municipioDao;
	}

	public List<Municipio> getListaMunicipioSelect() {
		return listaMunicipioSelect;
	}

	public void setListaMunicipioSelect(List<Municipio> listaMunicipioSelect) {
		this.listaMunicipioSelect = listaMunicipioSelect;
	}

	public LazerDao getLazerDao() {
		return lazerDao;
	}

	public void setLazerDao(LazerDao lazerDao) {
		this.lazerDao = lazerDao;
	}

	public DualListModel<Lazer> getLazeres() {
		return lazeres;
	}

	public void setLazeres(DualListModel<Lazer> lazeres) {
		this.lazeres = lazeres;
	}

	public List<Lazer> getListaLazerSel() {
		return listaLazerSel;
	}

	public void setListaLazerSel(List<Lazer> listaLazerSel) {
		this.listaLazerSel = listaLazerSel;
	}	
	
	

}
