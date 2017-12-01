package com.dsj.modules.easemob.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @描述: PO.
 * @作者: wangjl.
 * @创建时间: 2017-08-01 18:17:27.
 * @版本: 1.0 .
 */
public class EasemobUserPo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String passWord;
	private String nickName;
	private String newpassword;
	private ArrayList<String> friendNames;
	private ArrayList<String> blackListName;

	public EasemobUserPo() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public ArrayList<String> getFriendNames() {
		return friendNames;
	}

	public void setFriendNames(ArrayList<String> friendNames) {
		this.friendNames = friendNames;
	}

	public ArrayList<String> getBlackListName() {
		return blackListName;
	}

	public void setBlackListName(ArrayList<String> blackListName) {
		this.blackListName = blackListName;
	}

	public EasemobUserPo username(String userName) {
		this.userName = userName;
		return this;
	}

	public EasemobUserPo password(String passWord) {
		this.passWord = passWord;
		return this;
	}

	public EasemobUserPo nickname(String nickName) {
		this.nickName = nickName;
		return this;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass()) {
			return false;
		} else {
			EasemobUserPo easemobUser = (EasemobUserPo) o;
			return Objects.equals(userName, easemobUser.userName)
					&& Objects.equals(passWord, easemobUser.passWord)
					&& Objects.equals(nickName, easemobUser.nickName);
		}
	}

	public int hashCode() {
		return Objects.hash(new Object[] { userName, passWord, nickName });
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		sb.append("    ").append(toIndentedString(super.toString()))
				.append("\n");
		sb.append("}");
		return sb.toString();
	}

	private String toIndentedString(Object o) {
		if (o == null)
			return "null";
		return o.toString().replace("\n", "\n    ");
	}

}