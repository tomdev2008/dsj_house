package com.dsj.modules.newhouse.vo;

import java.io.Serializable;
import java.util.List;

import com.dsj.modules.newhouse.po.NewHouseTypeAuthPo;

/**
 * @author gaocj
 *
 */
public class NewHouseTypeCountVo implements Serializable {

	private Integer room;
	private String roomName;
	private Integer count;
	
	private List<NewHouseTypeAuthPo> houseTypeList;
	
	public List<NewHouseTypeAuthPo> getHouseTypeList() {
		return houseTypeList;
	}
	public void setHouseTypeList(List<NewHouseTypeAuthPo> houseTypeList) {
		this.houseTypeList = houseTypeList;
	}
	public Integer getRoom() {
		return room;
	}
	public void setRoom(Integer room) {
		this.room = room;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}
