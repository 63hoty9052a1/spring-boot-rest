package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_user")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private String userId;

	@Column(name = "pass_wd")
	private String passWord = "";

	@Column(name = "user_name")
	private String userName;

	@Column(name = "delete_flag")
	private Integer deleteFlag;

	@Embedded
	protected UserAddressInfo userAddressInfo;


	public UserAddressInfo getUserAddressInfo() {
		return userAddressInfo;
	}

	public void setUserAddressInfo(UserAddressInfo userAddressInfo) {
		this.userAddressInfo = userAddressInfo;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
