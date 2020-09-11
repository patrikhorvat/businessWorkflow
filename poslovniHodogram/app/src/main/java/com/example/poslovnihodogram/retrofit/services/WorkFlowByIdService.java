package com.example.poslovnihodogram.retrofit.services;

import com.example.poslovnihodogram.retrofit.pojo.WorkFlow;
import com.example.poslovnihodogram.settings.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WorkFlowByIdService {

    @GET
    Call<WorkFlow> getData(@Url String workflowId);

}
