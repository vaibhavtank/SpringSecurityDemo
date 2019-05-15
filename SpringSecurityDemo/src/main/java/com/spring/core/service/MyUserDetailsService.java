package com.spring.core.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spring.core.dao.UserDao;
import com.spring.core.model.UserRole;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

 /**
  * UserDao.
  */
 @Autowired
 private UserDao userDao;

 @Transactional(readOnly = true)
 @Override
 public final UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
  com.spring.core.model.User user = userDao.findByUserName(username);
  List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
  return buildUserForAuthentication(user, authorities);
 }

 /**
  * @param user
  * @param authorities
  * @return User
  */
 private User buildUserForAuthentication(final com.spring.core.model.User user, final List<GrantedAuthority> authorities) {
  return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
 }

 /**
  * @param userRoles
  * @return List<GrantedAuthority>
  */
 private List<GrantedAuthority> buildUserAuthority(final Set<UserRole> userRoles) {
  Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
  // Build user's authorities
  for (UserRole userRole : userRoles) {
   if (userRole.equals("ROLE_ADMIN")) {
    setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
   }
  }
  List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
  return Result;
 }
}