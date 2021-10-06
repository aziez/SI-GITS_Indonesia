package com.aziz.todoit.model.task;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TaskModel{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("message")
	private String message;

	@SerializedName("sucsess")
	private boolean sucsess;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setSucsess(boolean sucsess){
		this.sucsess = sucsess;
	}

	public boolean isSucsess(){
		return sucsess;
	}
}