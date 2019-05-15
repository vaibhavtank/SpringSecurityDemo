package com.spring.restdao.impl;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.spring.restdao.LoginDao;

/**
 * @author ADMIN
 */
@Repository("LoginDao")
public class LoginDaoImpl implements LoginDao {

 @Autowired
 private SessionFactory sessionFactory;


 /*
  * (non-Javadoc)
  * @see com.spring.restdao.LoginDao #updateTripData(java.lang.String, java.lang.String)
  */
 @Override
 public void updateTripData(String txid, String status) {
  StringBuffer query = new StringBuffer("update TripStatus set status='" + status + "' where tripID='" + txid + "'");
  sessionFactory.getCurrentSession().createQuery(query.toString()).executeUpdate();
 }


 /*
  * (non-Javadoc)
  * @see com.spring.restdao.LoginDao #updateOTP(int, java.lang.String)
  */
 @Override
 public void updateOTP(int driverid, String otpst) {
  StringBuffer query = new StringBuffer("update DriverDetail set otp='" + otpst + "' where driId='" + driverid + "'");
  sessionFactory.getCurrentSession().createQuery(query.toString()).executeUpdate();
 }

 /*
  * (non-Javadoc)
  * @see com.spring.restdao.LoginDao #updateTripDetails(java.lang.String, java.lang.String, java.lang.String,
  * java.lang.String)
  */
 @Override
 public void updateTripDetails(String tripid, String tripReadStatus, String tripReadDate, String tripReadTime) {
  StringBuffer query = new StringBuffer("update TripStatus set readstatus='" + tripReadStatus + "', readdate='" + tripReadDate
    + "',readtime='" + tripReadTime + "' where tripID='" + tripid + "'");
  sessionFactory.getCurrentSession().createQuery(query.toString()).executeUpdate();
 }
}