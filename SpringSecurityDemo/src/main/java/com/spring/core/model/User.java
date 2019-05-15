 package com.spring.core.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	/**
	 * **username**.
	 */
	private String username;
	/**
	 * **password**.
	 */
	private String password;
	/**
	 *  **enabled**.
	 */
	private boolean enabled;
	/**
	 *  **fullName**.
	 */
	private String fullName;
	/**
	 * **contactNo**.
	 */
	private String contactNo;
	/**
	 * **contactNo**.
	 */
	private String emailId;

	public final String getFullName() {
		return fullName;
	}

	public final void setFullName(final String fullName) {
		this.fullName = fullName;
	}

	public final String getContactNo() {
		return contactNo;
	}

	public final void setContactNo(final String contactNo) {
		this.contactNo = contactNo;
	}

	public final String getEmailId() {
		return emailId;
	}

	public final void setEmailId(final String emailId) {
		this.emailId = emailId;
	}

	
	/**
	 * **userRole**
	 */
	private Set<UserRole> userRole = new HashSet<UserRole>(0);

	/**
	 * **User Constructor**.
	 */
	public User() {
	}

	public User(String username, String password, boolean enabled,
	  final String fullName, final String emailId, final String contactNo) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.fullName = fullName;
		this.contactNo = contactNo;
		this.emailId = emailId;
	}

	public User(final String username, String password, final boolean enabled,
	  final Set<UserRole> userRole, final String fullName,
			final String emailId, final String contactNo) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
		this.fullName = fullName;
		this.contactNo = contactNo;
		this.emailId = emailId;
	}

	@Id
 @Column(name = "username", unique = true, nullable = false, length = 45)
 public final String getUsername() {
		return this.username;
	}

	public final void setUsername(final String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 60)
 public final String getPassword() {
		return this.password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "enabled", nullable = false)
 public final boolean isEnabled() {
		return this.enabled;
	}

	public final void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
 public final Set<UserRole> getUserRole() {
		return this.userRole;
	}

	public final void setUserRole(final Set<UserRole> userRole) {
		this.userRole = userRole;
	}
}