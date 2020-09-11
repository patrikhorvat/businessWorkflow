package com.example.poslovnihodogram.adapters.workflowadapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poslovnihodogram.MainMenuActivity;
import com.example.poslovnihodogram.R;
import com.example.poslovnihodogram.WorkFlowDetailsActivity;
import com.example.poslovnihodogram.adapters.Holder;

import java.util.ArrayList;

public class WorkFlowAdapter extends RecyclerView.Adapter<Holder> {

    private ArrayList<Item> mItems = new ArrayList<>();
    private MainMenuActivity mainMenuActivity;

    public WorkFlowAdapter(MainMenuActivity mainMenuActivity) {
        this.mainMenuActivity = mainMenuActivity;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = null;

        if(viewType == Item.TYPE_WORKFLOW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workflow_row, parent, false);

        }
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        switch (getItemViewType(position)){

            case Item.TYPE_WORKFLOW:
                bindWorkflow(holder, position);
                break;

        }

    }

    private void bindWorkflow(Holder holder, int position) {


        View container = holder.itemView;

        WorkflowItem workflowItem = (WorkflowItem)mItems.get(position);

        TextView title = container.findViewById(R.id.title);
        TextView description = container.findViewById(R.id.description);

        title.setText(workflowItem.getWorkFlow().getTitle());
        description.setText(workflowItem.getWorkFlow().getDescription());

        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(new AlphaAnimation(1.0f, 0.2f));
                Intent intent = new Intent( mainMenuActivity, WorkFlowDetailsActivity.class);

                intent.putExtra("workflow_id", workflowItem.getWorkFlow().getId());
                mainMenuActivity.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        int type = -1;

        if(mItems.get(position).getTypeItem() == Item.TYPE_WORKFLOW){
            type = Item.TYPE_WORKFLOW;
        }

        return type;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void addItem(Item item){
        mItems.add(item);
        notifyDataSetChanged();
    }
}
