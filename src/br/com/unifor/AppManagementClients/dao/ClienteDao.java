package br.com.unifor.AppManagementClients.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.unifor.AppManagementClients.model.Cliente;
import br.com.unifor.AppManagementClients.model.Lazer;
import br.com.unifor.AppManagementClients.model.UF;


public class ClienteDao extends DaoGenerico<Cliente, Integer>{
	@SuppressWarnings("unchecked")
	public List<Cliente> consulta(Cliente params) {
		Criteria criteria = criaCriteria().setResultTransformer(
				Criteria.DISTINCT_ROOT_ENTITY);
		//Add restri��o
		if ((params.getId() != null) && (params.getId() != 0)) {
			criteria.add(Restrictions.eq("id", params.getId())); 
		}	
		if ((params.getNome() != null)&& (params.getNome().length() > 0) ) {
			criteria.add(Restrictions.like("nome",params.getNome().trim(),MatchMode.ANYWHERE).ignoreCase());
		}
		if ((params.getEstadoCivil() != null)&& (params.getEstadoCivil()> 0) ) {
			criteria.add(Restrictions.eq("estadoCivil",params.getEstadoCivil()));
		}
		if ((params.getDataNascimento()!= null)) {
		    criteria.add(Restrictions.eq("dataNascimento", params.getDataNascimento()));
		}
		criteria.createAlias("listaLazer","listaLazer");
		if ((params.getListaLazer()!= null) && (params.getListaLazer().size()> 0)) {
			List<Integer> listaLazer = new ArrayList<Integer>();
			for (Lazer lazer : params.getListaLazer()){
				listaLazer.add(lazer.getId());				
			}
		    criteria.add(Restrictions.in("listaLazer.id",listaLazer));
		}	
		criteria.addOrder(Order.asc("nome")); // Ordenando por nome 
		return (List<Cliente>) criteria.list(); // Executa e gera a lista
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> consultaHql(Cliente params) {
		StringBuffer strHql = new StringBuffer("select cliente " +
			    " from Cliente cliente" +
			    " where cliente.id > 0");			   
		if ((params.getId() != null) && (params.getId() != 0)) {
			strHql.append(" and cliente.id = ?1");
		}	
		if ((params.getNome() != null)&& (params.getNome().length() > 0) ) {
			strHql.append(" and lower(cliente.nome) like lower(?2)");
		}
		if ((params.getEstadoCivil() != null)&& (params.getEstadoCivil()> 0) ) {
			strHql.append(" and cliente.estadoCivil = ?3");		
		}
		if ((params.getDataNascimento()!= null)) {
			strHql.append(" and cliente.dataNascimento = ?4");
		}
		
		if ((params.getListaLazer()!= null) && (params.getListaLazer().size()> 0)) {
			strHql.append(" and cliente.listaLazer.id in(?5)");
		}
			
		EntityManager entityManager = fabrica.createEntityManager();  
		Query query = entityManager.createQuery(strHql.toString());
		if ((params.getId() != null) && (params.getId() != 0)) {
			query.setParameter(1,params.getId());
		}	
		if ((params.getNome() != null)&& (params.getNome().length() > 0) ) {
			query.setParameter(2,"%" + params.getNome() + "%");
		}
		if ((params.getEstadoCivil() != null)&& (params.getEstadoCivil()> 0) ) {
			query.setParameter(3,params.getEstadoCivil());
		}
		if ((params.getDataNascimento()!= null)) {
			query.setParameter(4,params.getDataNascimento());
		}
		if ((params.getListaLazer()!= null) && (params.getListaLazer().size()> 0)) {
			List<Integer> listaLazer = new ArrayList<Integer>();
			for (Lazer lazer : params.getListaLazer()){
				listaLazer.add(lazer.getId());				
			}
			query.setParameter(5,listaLazer.toArray());
		}	
		return (List<Cliente>) query.getResultList(); // Executa e gera a lista
	}
	
	public List<Cliente> clientePorUF(UF params) {
		Criteria criteria = criaCriteria().setResultTransformer(
				Criteria.DISTINCT_ROOT_ENTITY);
		//Add restri��o
		criteria.createAlias("municipio","municipio");
		if ((params.getId() != null) && (params.getId() != 0)) {
			criteria.add(Restrictions.eq("municipio.uf",params)); 
		}		
		criteria.addOrder(Order.asc("nome")); // Ordenando por nome 
		return (List<Cliente>) criteria.list(); // Executa e gera a lista
	}
}