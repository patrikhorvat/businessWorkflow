package com.example.poslovnihodogram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poslovnihodogram.retrofit.api.ApiUtil;
import com.example.poslovnihodogram.retrofit.pojo.WorkFlow;
import com.example.poslovnihodogram.retrofit.services.WorkFlowByIdService;
import com.example.poslovnihodogram.settings.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkFlowDetailsActivity extends AppCompatActivity {

    private TextView title, description, label, id;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_flow_details2);

        id = findViewById(R.id.id);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        label = findViewById(R.id.label);
        progressBar = findViewById(R.id.loading);

        int ids = getIntent().getExtras().getInt("workflow_id");

        String id = String.valueOf(ids);

        if(id!=null) {
            downloadData(id);
        }

    }

    private void downloadData(String id) {

        progressBar.setVisibility(View.VISIBLE);
        WorkFlowByIdService workFlowByIdService = ApiUtil.getWorkFlowByIdService();
        workFlowByIdService.getData(Constants.workFlows+"/"+ id).enqueue(new Callback<WorkFlow>() {
            @Override
            public void onResponse(Call<WorkFlow> call, Response<WorkFlow> response) {
                if(response.isSuccessful()){
                    handleResponse(response.body());
                }else{
                    Toast.makeText(getApplicationContext(), "Nesto nije u redu, probajte ponovno", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WorkFlow> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), "Nesto nije u redu, probajte ponovno", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void handleResponse(WorkFlow body) {
        progressBar.setVisibility(View.GONE);
        if(body!=null){

            id.setText(String.valueOf(body.getId()));
            title.setText(body.getTitle());
            description.setText(body.getDescription());
            label.setText(body.getLabel());


        }
    }
}