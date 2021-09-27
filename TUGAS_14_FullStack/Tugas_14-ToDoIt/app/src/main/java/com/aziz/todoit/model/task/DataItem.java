package com.aziz.todoit.model.task;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("onCreate")
	private String onCreate;

	@SerializedName("taskName")
	private String taskName;

	@SerializedName("id")
	private int id;

	@SerializedName("status")
	private String status;

	public void setOnCreate(String onCreate){
		this.onCreate = onCreate;
	}

	public String getOnCreate(){
		return onCreate;
	}

	public void setTaskName(String taskName){
		this.taskName = taskName;
	}

	public String getTaskName(){
		return taskName;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}