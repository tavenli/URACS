package com.tavenli.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tavenli.entity.RoleEntity;
import com.tavenli.entity.UserEntity;

@Repository
public class RoleDao extends BaseDao {

	private static Logger logger = LoggerFactory.getLogger(RoleDao.class);
	
	public RoleEntity getRoleById(int id){
		try {
			return this.getById(RoleEntity.class, id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public RoleEntity getSingleRole(String roleName) throws Exception{
		String hql = "from RoleEntity where roleName=?";
		TypedQuery<RoleEntity> query = this.getEntityManager().createQuery(hql, RoleEntity.class);
		query.setParameter(1, roleName);
		return query.getSingleResult();
	}
	
	public RoleEntity getRole(String roleName){
		String hql = "from RoleEntity where roleName=?";
		TypedQuery<RoleEntity> query = this.getEntityManager().createQuery(hql, RoleEntity.class);
		query.setParameter(1, roleName);
		
		try {			
			List<RoleEntity> list = query.getResultList();
			if(list!=null && list.size()>0){
				return list.get(0);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			
		}
		
		return null;
		
	}
	
	/**
	 * @param status 0:禁用,1:启用
	 * @return
	 */
	public List<RoleEntity> getRoles(int status) {
		List<RoleEntity> list = new ArrayList<RoleEntity>();
		
		String hql = "from RoleEntity where status=?";
		TypedQuery<RoleEntity> query = this.getEntityManager().createQuery(hql, RoleEntity.class);
		query.setParameter(1, status);
		try {
			list = query.getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return list;
	}
	
	public int queryDataCount(Map<String, Object> paramMap){
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		
		hql.append("select count(*) from RoleEntity where 1=1");
		
		if(paramMap.get("id")!=null){
			hql.append(" and id=:id");
			params.put("id", paramMap.get("id"));
		}
		
		if(paramMap.get("roleName")!=null){
			hql.append(" and roleName like:roleName");
			params.put("roleName", "%"+ paramMap.get("roleName") +"%");
			
		}
		
		if(paramMap.get("status")!=null){
			hql.append(" and status=:status");
			params.put("status", paramMap.get("status"));
		}
		
		return super.queryPageTotalCount(hql.toString(),params).intValue();
	}
	
	public List<RoleEntity> queryPageData(int start, int maxSize,Map<String, Object> paramMap){
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		
		hql.append("from RoleEntity  where 1=1");
		
		if(paramMap.get("id")!=null){
			hql.append(" and id=:id");
			params.put("id", paramMap.get("id"));
		}
		
		if(paramMap.get("roleName")!=null){
			hql.append(" and roleName like:roleName");
			params.put("roleName", "%"+ paramMap.get("roleName") +"%");
			
		}
		
		if(paramMap.get("status")!=null){
			hql.append(" and status=:status");
			params.put("status", paramMap.get("status"));
		}
		
		hql.append(" order by id desc");
		
		return super.queryPageList(hql.toString(), params, start, maxSize);
	}
	
	
}
