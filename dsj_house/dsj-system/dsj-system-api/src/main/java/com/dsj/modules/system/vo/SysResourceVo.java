package com.dsj.modules.system.vo;
import java.io.Serializable;
import java.util.List;

import com.dsj.modules.system.po.FunctionPo;

public class SysResourceVo extends FunctionPo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<FunctionPo> resourcePo;
	

	public List<FunctionPo> getResourcePo() {
		return resourcePo;
	}

	public void setResourcePo(List<FunctionPo> resourcePo) {
		this.resourcePo = resourcePo;
	}

	public SysResourceVo(FunctionPo po){
		setId(po.getId());
		setIconUri(po.getIconUri());
		setCreateTime(po.getCreateTime());
		setName(po.getName());
		setNameCode(po.getNameCode());
		setPid(po.getPid());
		setSort(po.getSort());
		setUpdateTime(po.getUpdateTime());
		setUri(po.getUri());
	}
	
	public SysResourceVo(){
			
	}

}
