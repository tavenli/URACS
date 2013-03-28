package com.tavenli.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tavenli.entity.UserEntity;

@Repository
public class UserDao extends BaseDao {

	private static Logger logger = LoggerFactory.getLogger(UserDao.class);
	
	public UserEntity getSingleUser(String userName) throws Exception{
		String hql = "from UserEntity where userName=?";
		TypedQuery<UserEntity> query = this.getEntityManager().createQuery(hql, UserEntity.class);
		query.setParameter(1, userName);
		return query.getSingleResult();
	}
	
	public int queryDataCount(Map<String, Object> paramMap){
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		
		hql.append("select count(*) from UserEntity where 1=1");
		
		if(paramMap.get("id")!=null){
			hql.append(" and id=:id");
			params.put("id", paramMap.get("id"));
		}
		
		if(paramMap.get("userName")!=null){
			hql.append(" and userName like:userName");
			params.put("userName", "%"+ paramMap.get("userName") +"%");
			
		}
		
		if(paramMap.get("status")!=null){
			hql.append(" and status=:status");
			params.put("status", paramMap.get("status"));
		}
		
		return super.queryPageTotalCount(hql.toString(),params).intValue();
	}
	
	public List<UserEntity> queryPageData(int start, int maxSize,Map<String, Object> paramMap){
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		
		hql.append("from UserEntity  where 1=1");
		
		if(paramMap.get("id")!=null){
			hql.append(" and id=:id");
			params.put("id", paramMap.get("id"));
		}
		
		if(paramMap.get("userName")!=null){
			hql.append(" and userName like:userName");
			params.put("userName", "%"+ paramMap.get("userName") +"%");
			
		}
		
		if(paramMap.get("status")!=null){
			hql.append(" and status=:status");
			params.put("status", paramMap.get("status"));
		}
		
		hql.append(" order by id desc");
		
		return super.queryPageList(hql.toString(), params, start, maxSize);
	}
}
