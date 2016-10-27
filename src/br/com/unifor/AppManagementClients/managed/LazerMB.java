package br.com.unifor.AppManagementClients.managed;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.unifor.AppManagementClients.dao.LazerDao;
import br.com.unifor.AppManagementClients.model.Lazer;

@SessionScoped
@ManagedBean(name = "lazerMB")
public class LazerMB extends AbstractMB {

	private Lazer lazer;
	private List<Lazer> listagemResultadoBusca;
	protected LazerDao lazerDao = new LazerDao();

	public LazerMB() {
		lazer = new Lazer();
		listagemResultadoBusca = new ArrayList<Lazer>();
		total = 0;
	}

	public String init() {
		lazer = new Lazer();
		listagemResultadoBusca = new ArrayList<Lazer>();
		total = 0;
		return "/paginas/lazer/Lazer.xhtml?faces-redirect=true";
	}

	public void limpar() {
		lazer = new Lazer();
		listagemResultadoBusca = new ArrayList<Lazer>();
		total = 0;
	}

	// Pessistencia
	public String salvar() {
		try {
			getLazerDao().incluir(lazer);
			this.consultar();
			lazer = new Lazer();
			addInfoMessage("Opera��o efetuada com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage());
		}
		return "/paginas/lazer/Lazer.xhtml?faces-redirect=true";
	}

	public String atualizar() {
		try {
			getLazerDao().atualizar(lazer);
			lazer = new Lazer();
			this.consultar();
			addInfoMessage("Opera��o efetuada com sucesso.");
			return "/paginas/lazer/Lazer.xhtml?faces-redirect=true";

		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage());
			return null;
		}
	}

	public String excluir() {
		try {
			getLazerDao().excluir(lazer.getId());
			listagemResultadoBusca.remove(lazer);
			this.consultar();
			lazer = new Lazer();
			addInfoMessage("Opera��o efetuada com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage());
		}
		return null;
	}

	public String consultar() {
		listagemResultadoBusca = getLazerDao().consultaHql(lazer);
		total = listagemResultadoBusca.size();
		if (total == 0) {
			addInfoMessage("Nenhum Registro foi localizado.");
		}
		lazer = new Lazer();
		return null;
	}

	// Navega��o

	public String incluir() {
		lazer = new Lazer();
		return "/paginas/lazer/LazerIncluir.xhtml?faces-redirect=true";
	}

	public String editar() {
		return "/paginas/lazer/LazerEditar.xhtml?faces-redirect=true";
	}

	public String cancelar() {
		return "/paginas/lazer/Lazer.xhtml?faces-redirect=true";
	}

	// gets e setters

	public List<Lazer> getListagemResultadoBusca() {
		return listagemResultadoBusca;
	}

	public Lazer getLazer() {
		return lazer;
	}

	public void setLazer(Lazer lazer) {
		this.lazer = lazer;
	}

	public void setListagemResultadoBusca(List<Lazer> listagemResultadoBusca) {
		this.listagemResultadoBusca = listagemResultadoBusca;
	}

	public LazerDao getLazerDao() {
		return lazerDao;
	}

	public void setLazerDao(LazerDao lazerDao) {
		this.lazerDao = lazerDao;
	}

}
