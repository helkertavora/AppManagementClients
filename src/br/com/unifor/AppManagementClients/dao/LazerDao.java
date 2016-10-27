package br.com.unifor.AppManagementClients.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.unifor.AppManagementClients.model.Lazer;


public class LazerDao extends DaoGenerico<Lazer, Integer>{
	public List<Lazer> consulta(Lazer params) {
		Criteria criteria = criaCriteria().setResultTransformer(
				Criteria.DISTINCT_ROOT_ENTITY);
		//Add restri��o
		if ((params.getId() != null) && (params.getId() != 0)) {
			criteria.add(Restrictions.eq("id", params.getId())); 
		}	
		if ((params.getDescricao() != null)&& (params.getDescricao().length() > 0) ) {
			criteria.add(Restrictions.like("descricao",params.getDescricao().trim(),MatchMode.ANYWHERE).ignoreCase());
		}
		criteria.addOrder(Order.asc("descricao")); // Ordenando por nome 
		return (List<Lazer>) criteria.list(); // Executa e gera a lista
	}
	
	public List<Lazer> consultaHql(Lazer params) {
		StringBuffer strHql = new StringBuffer("select lazer " +
			    " from Lazer lazer" +
			    " where lazer.id > 0 ");			   
		if ((params.getId() != null) && (params.getId() != 0)) {
			strHql.append(" and lazer.id = ?1");
		}	
		if ((params.getDescricao() != null)&& (params.getDescricao().length() > 0) ) {
			strHql.append(" and lower(lazer.descricao) like lower(?3)");
		}
		EntityManager entityManager = fabrica.createEntityManager();
		Query query = entityManager.createQuery(strHql.toString());
		if ((params.getId() != null) && (params.getId() != 0)) {
			query.setParameter(1,params.getId());
		}	
		if ((params.getDescricao()!= null)&& (params.getDescricao().length() > 0) ) {
			query.setParameter(3,"%" + params.getDescricao() + "%");
		}
		return (List<Lazer>) query.getResultList(); // Executa e gera a lista
	}
}