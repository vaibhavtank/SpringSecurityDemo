package com.spring.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.core.dao.AbstractDao;
import com.spring.core.dao.UserDao;
import com.spring.core.model.User;

@Repository
public class UserDaoImpl extends AbstractDao implements UserDao {
 
 /*
  * (non-Javadoc)
  * 
  * @see com.spring.core.dao.UserDao #findByUserName(java.lang.String)
  */
 @SuppressWarnings("unchecked")
 public User findByUserName(final String username) {
  List<User> users = new ArrayList<User>();
  users = getSession().createQuery("from User where username=?").setParameter(0, username).list();
  if (users.size() > 0) {
   return users.get(0);
  } else {
   return null;
  }
 }
}