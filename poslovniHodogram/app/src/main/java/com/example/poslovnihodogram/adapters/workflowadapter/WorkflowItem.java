package com.example.poslovnihodogram.adapters.workflowadapter;

import com.example.poslovnihodogram.retrofit.pojo.WorkFlow;

public class WorkflowItem extends Item {

    WorkFlow workFlow;

    public WorkflowItem(WorkFlow workFlow) {
        this.workFlow = workFlow;
    }

    public WorkFlow getWorkFlow() {
        return workFlow;
    }

    public void setWorkFlow(WorkFlow workFlow) {
        this.workFlow = workFlow;
    }

    @Override
    public int getTypeItem() {
        return TYPE_WORKFLOW;
    }
}
