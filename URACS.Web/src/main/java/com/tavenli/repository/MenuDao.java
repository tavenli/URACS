package com.tavenli.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tavenli.entity.MenuEntity;

@Repository
public class MenuDao extends BaseDao {

	private static Logger logger = LoggerFactory.getLogger(MenuDao.class);
	
	
	public List<MenuEntity> getMenus() {
		List<MenuEntity> list = new ArrayList<MenuEntity>();
		
		String hql = "from MenuEntity";
		TypedQuery<MenuEntity> query = this.getEntityManager().createQuery(hql, MenuEntity.class);
		try {
			list = query.getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return list;
	}
	
	public List<MenuEntity> getChildMenus(int parentId) {
		List<MenuEntity> list = new ArrayList<MenuEntity>();
		
		String hql = "from MenuEntity where parentId=?";
		TypedQuery<MenuEntity> query = this.getEntityManager().createQuery(hql, MenuEntity.class);
		query.setParameter(1, parentId);
		try {
			list = query.getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return list;
	}
	
	
	
	
}
