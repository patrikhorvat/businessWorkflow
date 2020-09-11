package com.example.poslovnihodogram.retrofit.services;

import com.example.poslovnihodogram.retrofit.pojo.WorkFlowStep;
import com.example.poslovnihodogram.settings.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MainWorkflowsSteps {

    @GET(Constants.linWorkflowSteps)
    Call<ArrayList<WorkFlowStep>> getData();

}
