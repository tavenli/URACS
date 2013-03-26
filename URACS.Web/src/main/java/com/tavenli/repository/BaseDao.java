package com.tavenli.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseDao {

	
	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}
	

	public Query createQuery(String hql) {
		return entityManager.createQuery(hql);
	}

	@Transactional(readOnly = false)
	public void save(Object vo) throws Exception {
		try {
			entityManager.persist(vo);
		} catch (Exception e) {
			throw new Exception("新增失败", e);
		}
	}

	@Transactional(readOnly = false)
	public void delete(Object vo) throws Exception {
		try {
			entityManager.remove(entityManager.merge(vo));
		} catch (Exception e) {
			throw new Exception("删除失败", e);
		}
	}

	@Transactional(readOnly = false)
	public  <T>  T  update(Object vo) throws Exception {
		Object obj = entityManager.merge(vo);
		if(obj!=null){
			return (T)obj;
		}else{
			throw new Exception("更新数据失败");
		}
	}
	
	public Object getObjectById(Class<?> entityClass, Serializable id) {
		Object obj = entityManager.find(entityClass, id);
		return obj;
	}

	public <T>  T getById(Class<T> entityClass, Serializable id) throws Exception {
		Object obj = entityManager.find(entityClass, id);
		if(obj!=null){
			return (T)obj;
		}else{
			throw new Exception("查询的数据不存在");
		}
	}
	
	public boolean exist(Class<?> entityClass, Serializable id) {
		Object data = entityManager.find(entityClass, id);
		return data != null;
		
	}
	
	public Long queryCount(String hql) {
		Query query = this.createQuery(hql);
		Object count = query.getResultList().get(0);
		return count == null ? 0 : (Long) count;
	}
	
	/**
	 * @param hql 要使用参数名，例如 from CardInfoEntity t where t.cardId=:cardId
	 * @param params
	 * @param start
	 * @param maxSize
	 * @return
	 */
	public List queryPageList(String hql,Map<String, Object> params,  int start, int maxSize) {
		Query query = entityManager.createQuery(hql);
		
		if (params != null) {
			int i = 0;
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
				i++;
			}
		}
		
		if (maxSize == 0)
			return query.setFirstResult(start).getResultList();
		else
			return query.setFirstResult(start).setMaxResults(maxSize)
					.getResultList();

	}
	
	public Long queryPageTotalCount(String hql, Map<String, Object> params) {
		String chql = "select count(*) "
				+ hql.substring(hql.toLowerCase().indexOf("from"));
		Query query = entityManager.createQuery(chql);
		if (params != null) {
			int i = 0;
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
				i++;
			}
		}
		return (Long) query.getResultList().get(0);
	}
	
	public List queryPageList(DetachedCriteria detachedCriteria, int start, int maxSize){
		
		Session session = (Session) entityManager.getDelegate();

        Criteria criteria = detachedCriteria.getExecutableCriteria(session);   

        int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();   

        criteria.setProjection(null);   

        List list = criteria.setFirstResult(start).setMaxResults(maxSize).list();  
        
        return list;
        
	}
	
	public Long queryPageCount(String hql,Map<Integer, Object> params) {
		Query query = entityManager.createQuery(hql);
		
		if (params != null) {
			int i = 0;
			for (Integer key : params.keySet()) {
				query.setParameter(key, params.get(key));
				i++;
			}
		}
		
		Object count = query.getResultList().get(0);
		return count == null ? 0 : (Long) count;
		
	}
	
	public int executeHqlByIndexParams(String hql,Map<Integer, Object> params){
		
		Query query = entityManager.createQuery(hql);
		
		if (params != null) {
			int i = 0;
			for (Integer key : params.keySet()) {
				query.setParameter(key, params.get(key));
				i++;
			}
		}
		
		return query.executeUpdate();
		
	}
	
	public int executeHql(String hql,Map<String, Object> params){
		
		Query query = entityManager.createQuery(hql);
		
		if (params != null) {
			int i = 0;
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
				i++;
			}
		}
		
		return query.executeUpdate();
		
	}
	
	public List queryPageData(String hql,Map<Integer, Object> params,  int start, int maxSize) {
		Query query = entityManager.createQuery(hql);
		
		if (params != null) {
			int i = 0;
			for (Integer key : params.keySet()) {
				query.setParameter(key, params.get(key));
				i++;
			}
		}
		
		if (maxSize == 0)
			return query.setFirstResult(start).getResultList();
		else
			return query.setFirstResult(start).setMaxResults(maxSize)
					.getResultList();

	}
	

	

	
}

