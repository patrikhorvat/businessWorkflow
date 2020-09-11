package com.example.poslovnihodogram.retrofit.services;

import com.example.poslovnihodogram.settings.Constants;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserRegisterService {

    @POST(Constants.userRegister)
    @FormUrlEncoded
    Call<ResponseBody> getPost(@FieldMap HashMap<String, String> userData);

}
