package br.com.unifor.AppManagementClients.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;

public class DaoGenerico<T, ID extends Serializable>{

	protected static EntityManagerFactory fabrica = Persistence
	.createEntityManagerFactory("myPU");
	
	private final Class<T> classePersistente;

	@SuppressWarnings("unchecked")
	public DaoGenerico() {
		this.classePersistente = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Class<T> getClassePersistente() {
		return classePersistente;
	}

	protected final Criteria criaCriteria() {
		EntityManager entityManager = fabrica.createEntityManager();
		Session session = (Session) entityManager.getDelegate();
		return session.createCriteria(getClassePersistente());
	}

	public Class<T> getObjectClass() {
		return this.classePersistente;
	}

	
	
	public T incluir(T object) {
		EntityManager entityManager = fabrica.createEntityManager();
		EntityTransaction transacao = entityManager.getTransaction();
		transacao.begin();
		entityManager.persist(object);
		transacao.commit();
		entityManager.close();
		return object;
	}

	
	public List<T> todos(String ordem) {
		EntityManager entityManager = fabrica.createEntityManager();
		StringBuffer queryS = new StringBuffer("SELECT obj FROM "
				+ classePersistente.getSimpleName() + " obj ");
		if (ordem != null) {
			queryS.append("order by " + ordem);
		}
		Query query = entityManager.createQuery(queryS.toString());
		return  query.getResultList();
	}
	
		
	public T atualizar(T object) {
		EntityManager entityManager = fabrica.createEntityManager();
		EntityTransaction transacao = entityManager.getTransaction();
		transacao.begin();
		entityManager.merge(object);
		transacao.commit();
		entityManager.close();
		return null;
	}

	
	
	public void excluir(T object) {
		EntityManager entityManager = fabrica.createEntityManager();
		EntityTransaction transacao = entityManager.getTransaction();
		transacao.begin();
		entityManager.find(getClassePersistente(),object);;
		entityManager.remove(object);
		transacao.commit();
		entityManager.close();
	}

	public void excluir(ID id) {
		EntityManager entityManager = fabrica.createEntityManager();
		EntityTransaction transacao = entityManager.getTransaction();
		transacao.begin();
		T object = entityManager.find(getClassePersistente(),id);;
		entityManager.remove(object);
		transacao.commit();
		entityManager.close();
	} 
	
	public T buscaPorId(ID id) {
		EntityManager entityManager = fabrica.createEntityManager(); 
		return entityManager.find(getClassePersistente(), id);
	}
}
