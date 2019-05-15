package com.spring.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao {

	/**
	 * **SessionFatory**.
	 */
	@Autowired
	private SessionFactory sessionFactory;

	protected final Session getSession() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (Exception exp) {
			return sessionFactory.openSession();
		}
	}

	public final void persist(final Object entity) {
		getSession().persist(entity);
	}

	public final void delete(final Object entity) {
		getSession().delete(entity);
	}
}