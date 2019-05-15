package com.spring.config.core;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

 private static int totalActiveSessions;

 /**
  * @return int
  */
 public static int getSessionCount() {
  return totalActiveSessions;
 }

 /* (non-Javadoc)
  * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
  */
 @Override
 public void sessionCreated(HttpSessionEvent event) {
  System.out.println("========session created==========");
  totalActiveSessions++;
  event.getSession().setMaxInactiveInterval(15 * 60);
 }

 /* (non-Javadoc)
  * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
  */
 @Override
 public void sessionDestroyed(HttpSessionEvent event) {
  System.out.println("============session destroyed===============");
  totalActiveSessions--;
 }
}