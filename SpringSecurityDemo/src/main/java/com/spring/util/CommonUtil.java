package com.spring.util;

import java.text.SimpleDateFormat;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class CommonUtil {

	public static boolean checkUserLogin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return true;
		} else {
			return false;
		}
	}

	public static String getDateBySqlDate(final java.sql.Date sqlDate) {
		SimpleDateFormat viewFormat = new SimpleDateFormat("dd/MM/yyyy");
		String sqlDateFormat = viewFormat.format(sqlDate);
		return sqlDateFormat;
	}

	public static String getUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

	public static void getTokenByContactNo(final String tokenID) {
		final String serverKey = "AIzaSyDdpkKq0G5jliciPOA5qR9bJH5tvuruQc0";
		Thread t = new Thread() {
			public void run() {
				try {
					Sender sender = new com.spring.supportclasses.FCMSender(serverKey);
					Message message = new Message.Builder().collapseKey("message").timeToLive(3).delayWhileIdle(true)
							.addData("message", "Your Trip has been completed. Please submit your feedback").build();
					Result result = sender.send(message, tokenID, 1);
					System.err.println("result:::::::::"+result);
					System.err.println("tokenID:::::::::"+tokenID);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
		try {
			t.join();
		} catch (InterruptedException iex) {
			iex.printStackTrace();
		}
	}
}