package br.com.unifor.AppManagementClients.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.unifor.AppManagementClients.model.Municipio;


public class MunicipioDao extends DaoGenerico<Municipio, Integer>{
	public List<Municipio> consulta(Municipio params) {
		Criteria criteria = criaCriteria().setResultTransformer(
				Criteria.DISTINCT_ROOT_ENTITY);
		//Add restri��o
		if ((params.getId() != null) && (params.getId() != 0)) {
			criteria.add(Restrictions.eq("id", params.getId())); 
		}	
		/*if ((params. getNome() != null) && (params.getNome().length() > 0)) {
			criteria.add(Restrictions.eq("nome", params.getNome()).ignoreCase());
		}*/
		if ((params.getNome() != null)&& (params.getNome().length() > 0) ) {
			criteria.add(Restrictions.like("nome",params.getNome().trim(),MatchMode.ANYWHERE).ignoreCase());
		}
		if ((params.getUf()!= null) && (params.getUf().getId() > 0) ) {
			criteria.add(Restrictions.eq("uf",params.getUf()));
		}
		criteria.addOrder(Order.asc("nome")); // Ordenando por nome 
		return (List<Municipio>) criteria.list(); // Executa e gera a lista
	}
	public List<Municipio> consultaHql(Municipio params) {
		StringBuffer strHql = new StringBuffer("select municipio " +
			    " from Municipio municipio" +
			    " where municipio.id > 0");			   
		if ((params.getId() != null) && (params.getId() != 0)) {
			strHql.append(" and municipio.id = ?1");
		}	
		if ((params.getNome() != null)&& (params.getNome().length() > 0) ) {
			strHql.append(" and lower(municipio.nome) like lower(?2)");			
		}
		if ((params.getUf()!= null) && (params.getUf().getId() > 0) ) {
			strHql.append(" and municipio.uf.id =?3");
		}
		strHql.append(" order by nome");
		EntityManager entityManager = fabrica.createEntityManager();  
		Query query = entityManager.createQuery(strHql.toString());
		if ((params.getId() != null) && (params.getId() != 0)) {
			query.setParameter(1,params.getId());
		}	
		if ((params.getNome() != null)&& (params.getNome().length() > 0) ) {
			query.setParameter(2,"%" + params.getNome() + "%");
		}
		if ((params.getUf()!= null) && (params.getUf().getId() > 0) ) {
			query.setParameter(3,params.getUf().getId());
		}
		return (List<Municipio>) query.getResultList(); // Executa e gera a lista
	}
}