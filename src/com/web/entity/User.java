package com.web.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_USER")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户对象
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
	private String userName;//用户名
	
	@Column(length=62)
	private String password;//密码
	
	@Column(length=32)
	private String email;//邮箱

	@Column(length=22)
	private String phone;//电话

	@Column(length=22)
	private String nickname;//昵称

	@Column(length=8)
	private Integer status=1;//状态

	@ManyToOne
	private Role role;
	
	@Column
	private boolean enabled = true;//是否能登陆

	@Column
	private Date dateCreated;//创建时间

	@Column
	private Date lastUpdated;//最后一次修改时间

	@Column(length=32)
	private Long lastLoginTime;//最后一次登陆时间

	@Column(length=132)
	private String icon;//头像路径

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"userName\":\"" + userName
				+ "\", \"password\":\"" + password + "\", \"email\":\"" + email
				+ "\", \"phone\":\"" + phone + "\", \"nickname\":\"" + nickname
				+ "\", \"status\":\"" + status + "\", \"role\":\"" + role
				+ "\", \"enabled\":\"" + enabled + "\", \"dateCreated\":\""
				+ dateCreated + "\", \"lastUpdated\":\"" + lastUpdated
				+ "\", \"lastLoginTime\":\"" + lastLoginTime
				+ "\", \"icon\":\"" + icon + "\"}";
	}
	
	
}
