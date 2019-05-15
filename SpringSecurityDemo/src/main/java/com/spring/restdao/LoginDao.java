package com.spring.restdao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;


/**
 * @author ADMIN
 */
@Transactional
public interface LoginDao {


 void updateTripData(String txid, String status);

 void updateOTP(int driverid, String otpst);

 void updateTripDetails(String tripid, String tripreadstatus, String tripreaddate, String tripreadtime);

}