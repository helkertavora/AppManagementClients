package br.com.unifor.AppManagementClients.dao;

import java.util.List;

import br.com.unifor.AppManagementClients.model.UF;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UFDao extends DaoGenerico<UF, Integer>{
	public List<UF> consulta(UF params) {
		Criteria criteria = criaCriteria().setResultTransformer(
				Criteria.DISTINCT_ROOT_ENTITY);
		//Add restri��o
		if ((params.getId() != null) && (params.getId() != 0)) {
			criteria.add(Restrictions.eq("id", params.getId())); 
		}	
		if ((params.getSigla()!= null) && (params.getSigla().length() > 0)) {
			criteria.add(Restrictions.eq("sigla", params.getSigla()).ignoreCase());
		}
		if ((params.getNome() != null)&& (params.getNome().length() > 0) ) {
			criteria.add(Restrictions.like("nome",params.getNome().trim(),MatchMode.ANYWHERE).ignoreCase());
		}
		criteria.addOrder(Order.asc("nome")); // Ordenando por nome 
		return (List<UF>) criteria.list(); // Executa e gera a lista
	}
	
	public List<UF> consultaHql(UF params) {
		StringBuffer strHql = new StringBuffer("select uf " +
			    " from UF uf" +
				" where uf.id > 0" );			   
		if ((params.getId() != null) && (params.getId() != 0)) {
			strHql.append(" and uf.id = ?1");
		}	
		if ((params.getSigla()!= null) && (params.getSigla().length() > 0)) {
			strHql.append(" and uf.sigla = ?2");
		}
		if ((params.getNome() != null)&& (params.getNome().length() > 0) ) {
			strHql.append(" and lower(uf.nome) like lower(?3)");
		}
		EntityManager entityManager = fabrica.createEntityManager();
		Query query = entityManager.createQuery(strHql.toString());
		if ((params.getId() != null) && (params.getId() != 0)) {
			query.setParameter(1,params.getId());
		}	
		if ((params.getSigla()!= null) && (params.getSigla().length() > 0)) {
			query.setParameter(2,params.getSigla());
		}
		if ((params.getNome() != null)&& (params.getNome().length() > 0) ) {
			query.setParameter(3,"%" + params.getNome() + "%");
		}
		return (List<UF>) query.getResultList(); // Executa e gera a lista
	}
	
}