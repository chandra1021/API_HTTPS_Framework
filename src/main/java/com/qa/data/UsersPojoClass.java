package com.qa.data;

/*POJO= plane old java object*/
public class UsersPojoClass {
	
	String name;
	String job;
	String id;
	String createdAt;
	
	

	public UsersPojoClass() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public UsersPojoClass(String name, String job) {
		this.name = name;
		this.job = job;
	}
	
	

}
