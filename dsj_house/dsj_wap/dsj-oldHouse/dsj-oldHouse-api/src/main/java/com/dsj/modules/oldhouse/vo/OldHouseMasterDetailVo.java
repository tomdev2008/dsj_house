package com.dsj.modules.oldhouse.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.dsj.modules.oldhouse.po.OldHouseAgentPo;
import com.dsj.modules.oldhouse.po.OldHousePicturePo;

public class OldHouseMasterDetailVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OldHouseMasterAppVo oldHouseMaster;
	
	private List<OldHousePicturePo> oldHousePictures;
	
	private HouseDirectoryAppVo dicPo;

	private List<OldHouseRecommendVo> recommendVos;
	
	private List<OldHouseAgentPo> listAgents;
	
	private Map<String,Object> groupTypesMap;
	
	private Boolean checked;//
	
	private Boolean loginStatus;
	
	public OldHouseMasterAppVo getOldHouseMaster() {
		return oldHouseMaster;
	}

	public void setOldHouseMaster(OldHouseMasterAppVo oldHouseMaster) {
		this.oldHouseMaster = oldHouseMaster;
	}

	public List<OldHousePicturePo> getOldHousePictures() {
		return oldHousePictures;
	}

	public void setOldHousePictures(List<OldHousePicturePo> oldHousePictures) {
		this.oldHousePictures = oldHousePictures;
	}

	public HouseDirectoryAppVo getDicPo() {
		return dicPo;
	}

	public void setDicPo(HouseDirectoryAppVo dicPo) {
		this.dicPo = dicPo;
	}

	public List<OldHouseRecommendVo> getRecommendVos() {
		return recommendVos;
	}

	public void setRecommendVos(List<OldHouseRecommendVo> recommendVos) {
		this.recommendVos = recommendVos;
	}

	public Map<String, Object> getGroupTypesMap() {
		return groupTypesMap;
	}

	public void setGroupTypesMap(Map<String, Object> groupTypesMap) {
		this.groupTypesMap = groupTypesMap;
	}

	public List<OldHouseAgentPo> getListAgents() {
		return listAgents;
	}

	public void setListAgents(List<OldHouseAgentPo> listAgents) {
		this.listAgents = listAgents;
	}

	
	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(Boolean loginStatus) {
		this.loginStatus = loginStatus;
	}
	
	
}
