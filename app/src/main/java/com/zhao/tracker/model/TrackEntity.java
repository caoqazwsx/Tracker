package com.zhao.tracker.model;


/**   
 * @Title: Entity
 * @Description: 追踪位置
 * @author zhao
 * @date 2017-04-19 14:14:23
 * @version V1.0   
 *
 */


public class TrackEntity implements java.io.Serializable {

	private String id;

	private String location;

	private String description;

	private Long lastTime;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getLastTime() {
		return lastTime;
	}

	public void setLastTime(Long lastTime) {
		this.lastTime = lastTime;
	}
}
