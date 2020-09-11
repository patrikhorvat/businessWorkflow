package com.example.poslovnihodogram.retrofit.api;


import com.example.poslovnihodogram.retrofit.services.MainWorkflowsSteps;
import com.example.poslovnihodogram.retrofit.services.UserRegisterService;
import com.example.poslovnihodogram.retrofit.services.WorkFlowByIdService;
import com.example.poslovnihodogram.retrofit.services.WorkFlows;

public class ApiUtil {

    private ApiUtil() {}

    public static UserRegisterService getUserRegisterService(){
        return RetrofitClient.getJsonClient().create(UserRegisterService.class);
    }

    public static MainWorkflowsSteps getWorkFlowsStep(){
        return RetrofitClient.getJsonClient().create(MainWorkflowsSteps.class);
    }

    public static WorkFlows getWorkFlows(){
        return RetrofitClient.getJsonClient().create(WorkFlows.class);
    }

    public static WorkFlowByIdService getWorkFlowByIdService(){
        return RetrofitClient.getJsonClient().create(WorkFlowByIdService.class);
    }


}
