package com.purple.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import com.purple.commons.RequestHolder;
import com.purple.dao.UserRepository;
import com.purple.entities.User;

@Service
public class TotalService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Resource
	private UserRepository userRepository;


	@SuppressWarnings("deprecation")
	public List<Map> total(String u, String s) {
		
		String sql = "SELECT\r\n" + 
				"	count( s.id ) AS students,\r\n" + 
				"	count( IF ( s.is_state = 1, TRUE, NULL ) ) AS nstate,\r\n" + 
				"	count( IF ( s.is_state = 0, TRUE, NULL ) ) AS state,\r\n" + 
				"	`user`.`name` as uname\r\n" + 
				"FROM\r\n" + 
				"	student AS s\r\n" + 
				"	LEFT JOIN `user` ON s.user_id = USER.id\r\n" + 
				"	LEFT JOIN speciality ON s.speciality_id = speciality.id \r\n" + 
				"WHERE\r\n" + 
				"	s.user_id LIKE '%"+u+"%'\r\n" + 
				"	AND s.speciality_id LIKE '%"+s+"%'";
		Session session = entityManager.unwrap(org.hibernate.Session.class);
		SQLQuery query = session.createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List list = query.list();
		return list;
	}


	public List<User> tUser() {
		//TODO 判断用户类型
		User user = RequestHolder.getCurrentUser();
		int type = user.getType();
		List<User> list = new ArrayList<>(); 
		switch (type) {
		case 0:
			list = userRepository.findUserByType(3);
			break;
		case 1:
			// TODO 查市
			List<User> sList = userRepository.findUserByParentId(user.getId());
			List<Integer> pList = new ArrayList<>();
			for (User u: sList) {
				pList.add(u.getId());
			}
			// TODO 查学校
			list = userRepository.findAllByParentIdIn(pList);
			break;
		case 2:
			// TODO 查所有学校
			list = userRepository.findUserByParentId(user.getId()); 
			break;
		case 3:
			list.add(user);
			break;
		}
		return list;
	}
	
}
