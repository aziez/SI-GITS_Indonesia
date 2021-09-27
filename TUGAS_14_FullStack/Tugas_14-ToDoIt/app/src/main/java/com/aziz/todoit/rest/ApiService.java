package com.aziz.todoit.rest;

import com.aziz.todoit.model.task.TaskModel;
import com.aziz.todoit.model.user.DataItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/user")
    Call<DataItem> login(@Query("username") String username,
                         @Query("password") String password);

    @FormUrlEncoded
    @POST("/register")
    Call<DataItem> postRegister(@Field("username") String username,
                                 @Field("email") String email,
                                 @Field("password") String password);

    @GET("/todo")
    Call<TaskModel> listTodo();

    @FormUrlEncoded
    @POST("/todo/add")
    Call<ArrayList<TaskModel>> postAddTask(@Field("task") String taskName,
                                          @Field("stat") String status);

    @FormUrlEncoded
    @POST("/todo/delete")
    Call<TaskModel> deleteTodo(@Field("id") Integer id);

    @FormUrlEncoded
    @POST("/todo/edit")
    Call<TaskModel> editTodo(@Field("id") Integer id,
                             @Field("task") String taskName,
                             @Field("stat") String status);

}
