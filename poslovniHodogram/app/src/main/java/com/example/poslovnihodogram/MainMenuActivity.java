package com.example.poslovnihodogram;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.poslovnihodogram.adapters.workflowadapter.WorkFlowAdapter;
import com.example.poslovnihodogram.adapters.workflowadapter.WorkflowItem;
import com.example.poslovnihodogram.retrofit.api.ApiUtil;
import com.example.poslovnihodogram.retrofit.pojo.WorkFlow;
import com.example.poslovnihodogram.retrofit.services.WorkFlows;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainMenuActivity extends AppCompatActivity {

     ProgressBar loadingProgressBar;
     private WorkFlowAdapter workFlowAdapter;
     private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        loadingProgressBar = findViewById(R.id.loading);
        recyclerView = findViewById(R.id.recyclerView);

        final TextView loggedUserTV = findViewById(R.id.headerTV);
        String value = getIntent().getExtras().getString("name");
        value = "Prijavljeni kao: " + value;
        loggedUserTV.setText(value);


        downloadMainData();

    }

    private void downloadMainData() {

        loadingProgressBar.setVisibility(View.VISIBLE);

        WorkFlows workFlows = ApiUtil.getWorkFlows();
        workFlows.getData().enqueue(new Callback<ArrayList<WorkFlow>>() {
            @Override
            public void onResponse(Call<ArrayList<WorkFlow>> call, Response<ArrayList<WorkFlow>> response) {
                if(response.isSuccessful()){
                    prepareData(response.body());
                }else{
                    Toast.makeText(getApplicationContext(), "Još jednom provjerite unesene podatke", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<WorkFlow>> call, Throwable t) {

            }
        });

    }

    private void prepareData(ArrayList<WorkFlow> body) {

        prepareAdapter();

        loadingProgressBar.setVisibility(View.GONE);
        if(body!=null){
            if(body.size()>0){
                for(int i = 0; i < body.size(); i++){
                    workFlowAdapter.addItem(new WorkflowItem(body.get(i)));
                }
            }else{
                Toast.makeText(getApplicationContext(), "Još jednom provjerite unesene podatke", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Još jednom provjerite unesene podatke", Toast.LENGTH_SHORT).show();
        }
    }

    private void prepareAdapter() {

        workFlowAdapter = new WorkFlowAdapter(this);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(workFlowAdapter);
    }
}
