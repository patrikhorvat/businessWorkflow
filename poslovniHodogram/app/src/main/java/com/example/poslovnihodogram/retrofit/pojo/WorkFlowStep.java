package com.example.poslovnihodogram.retrofit.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorkFlowStep {

    @SerializedName("Id")
    @Expose
    private Integer id = -1;
    @SerializedName("Name")
    @Expose
    private String name = "";
    @SerializedName("Description")
    @Expose
    private String description = "";
    @SerializedName("WorkflowId")
    @Expose
    private Integer workflowId = -1;
    @SerializedName("StepNumber")
    @Expose
    private Integer stepNumber = -1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Integer workflowId) {
        this.workflowId = workflowId;
    }

    public Integer getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }

}

