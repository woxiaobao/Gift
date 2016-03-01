package com.web.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_ROLE")
public class Role {
	
	/**
	 * @author lvbaolin
	 * @date 2015-06-15
	 * @version 1.0
	 */
	
	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid") 
    @Column(length=82)
	private String id;
	
	@Column(length=32)
	private String roleName;//角色名称
	
	@Column(length=32)
	private String comment;//角色说明
	
	@Column
	private boolean enabled = true;//能否使用

	@ManyToMany
	@JoinTable(name="role_module")
	private Set<Module> setModule;
	
	@Column
	private Date dateCreated;
	
	@Column
	private Date lastUpdated;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<Module> getSetModule() {
		return setModule;
	}

	public void setSetModule(Set<Module> setModule) {
		this.setModule = setModule;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	

}
