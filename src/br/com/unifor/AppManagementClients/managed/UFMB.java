package br.com.unifor.AppManagementClients.managed;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.unifor.AppManagementClients.model.Cliente;
import br.com.unifor.AppManagementClients.model.UF;
@SessionScoped
@ManagedBean(name="ufMB")
public class UFMB extends AbstractMB {
	private UF uf;
	private List<UF> listagemResultadoBusca;
	protected Map<String, Locale> locales = new HashMap<String, Locale>();
	private static String localeEscolhido;
	
	public void limpar() {
		uf = new UF();
		listagemResultadoBusca = new ArrayList<UF>();
		total = 0;		
	}
		
	public UFMB() {
		limpar();
		locales.put("pt_BR", new Locale("pt", "BR"));
	    locales.put("en_US", new Locale("en", "US"));
	    localeEscolhido = FacesContext.getCurrentInstance().getViewRoot().getLocale().toString();
	}
 
 	public String init() {
 		limpar();
		return "/paginas/uf/UF.xhtml?faces-redirect=true";
	}
	
 	
 	public String getLocaleEscolhido() {
 	    return localeEscolhido;
 	}
 	 
 	
 	public void mudarLocalidade(ActionEvent event) {
 		try {
 			String current = event.getComponent().getId();
 			FacesContext fc = FacesContext.getCurrentInstance();
 			Locale locale = locales.get(current);
 			if (locale != null) {
 				localeEscolhido =current;
 				fc.getViewRoot().setLocale(locale);
 			}else {
 				addErrorMessage(getMessage("locale_erro_nao_encontrado"));
 				}
 			} catch (Exception e) {
 				e.printStackTrace();
 				addErrorMessage(e.getMessage());
 			}
 	}



 	
 	    //Pessistencia
 		
 	public String salvar() {
 			try {
 					getUfDao().incluir(uf); 									
 					this.consultar();				
 					uf = new UF();
 					addInfoMessage("Opera��o efetuada com sucesso.");			    
 			} catch (Exception e) {
 				e.printStackTrace();
 				addErrorMessage(e.getMessage());
 			}
 			return "/paginas/uf/UF.xhtml?faces-redirect=true";
 	}
 		
 		
	public String atualizar() {
		try {
			getUfDao().atualizar(uf);
			this.consultar();
			uf = new UF();
			addInfoMessage("Opera��o efetuada com sucesso.");
			return "/paginas/uf/UF.xhtml?faces-redirect=true";						
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage());
			return null;
		}
	}

	public String  excluir() {
		try {
			getUfDao().excluir(uf.getId());			 	
			this.consultar();
			uf = new UF();
			addInfoMessage("Opera��o efetuada com sucesso.");			
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage());
		}
		return null;
	}
		
	public String consultar() {	
		listagemResultadoBusca =  getUfDao().consultaHql(uf);
		total = listagemResultadoBusca.size();
		if (total == 0) {
			addInfoMessage("Nenhum Registro foi localizado.");
		}		
		return null;
	}	
	
	public void relatorio() {		
		listagemResultadoBusca =  getUfDao().consulta(uf);		
		HashMap paramRel = new HashMap();
		String nomeRelatorio = "relUF";		
		gerarRelatorio(nomeRelatorio, paramRel,listagemResultadoBusca);		
	}    
    
    public void relatorioSubRel() {    	 
		listagemResultadoBusca =  getUfDao().consulta(uf);
		List<Cliente> listagemSubRel = getClienteDao().clientePorUF(uf);
		HashMap paramRel = new HashMap();
		String nomeRelatorio = "relUFCSREL";
		String subNomeRelatorio = "subRelCliente";
		gerarRelatorioSub(nomeRelatorio, paramRel,listagemResultadoBusca,listagemSubRel,subNomeRelatorio);		
	}
	
	
	public String incluir() {
		uf = new UF();		
		return "/paginas/uf/UFIncluir.xhtml?faces-redirect=true";
	}	
   
	public String editar() {
		return "/paginas/uf/UFEditar.xhtml?faces-redirect=true";
	}

	public String cancelar() {
		return "/paginas/uf/UF.xhtml?faces-redirect=true";
	}
		
   	// gets e setters
		
	public List<UF> getListagemResultadoBusca() {
		return listagemResultadoBusca;
	}

	
	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}
	
	public void setListagemResultadoBusca(List<UF> listagemResultadoBusca) {
		this.listagemResultadoBusca = listagemResultadoBusca;
	}	

}
