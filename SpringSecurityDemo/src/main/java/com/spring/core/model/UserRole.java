package com.spring.core.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Api(value = "Spring security", tags = "User roles", description = "User role description")
@Table(name = "user_roles", uniqueConstraints = @UniqueConstraint(
        columnNames = {"role", "username"}))
public class UserRole {
    /**
     * **userRoleId**
     */
    private Integer userRoleId;
    /**
     * **user**
     */
    private User user;
    /**
     * **role**
     */
    private String role;

    public UserRole() {
    }

    public UserRole(final User user, final String role) {
        this.user = user;
        this.role = role;
    }

    @Id
    @ApiModelProperty(notes = "It will generate Id auto increment")
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_role_id", unique = true, nullable = false)
    public final Integer getUserRoleId() {
        return this.userRoleId;
    }

    public final void setUserRoleId(final Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    @ApiModelProperty(notes = "Username")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    public final User getUser() {
        return this.user;
    }

    public final void setUser(final User user) {
        this.user = user;
    }

    @ApiModelProperty(notes = "Type of role")
    @Column(name = "role", nullable = false, length = 45)
    public final String getRole() {
        return this.role;
    }

    public final void setRole(final String role) {
        this.role = role;
    }

}