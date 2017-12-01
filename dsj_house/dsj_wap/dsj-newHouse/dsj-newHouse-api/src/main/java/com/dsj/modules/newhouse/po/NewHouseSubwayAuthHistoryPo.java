package com.dsj.modules.newhouse.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-07-24 16:36:40.
 * @版本: 1.0 .
 */
public class NewHouseSubwayAuthHistoryPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long houseId;		// 新房id
	private Long line;		// 线路id
	private String lineName;		// line_name
	private Long station;		// 站点
	private String stationName;		// station_name
	private Integer distance;		// 路程
	
	public NewHouseSubwayAuthHistoryPo() {
		super();
	}

	public NewHouseSubwayAuthHistoryPo(Long id){
		this();
	}
	

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
	
	public Long getLine() {
		return line;
	}

	public void setLine(Long line) {
		this.line = line;
	}
	
	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	
	public Long getStation() {
		return station;
	}

	public void setStation(Long station) {
		this.station = station;
	}
	
	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	
	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	
}