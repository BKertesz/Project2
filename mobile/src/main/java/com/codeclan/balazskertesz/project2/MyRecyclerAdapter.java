package com.codeclan.balazskertesz.project2;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<ListViewHolder> {

    private List<Task> tasks;
    private View.OnClickListener clickListener;

    public MyRecyclerAdapter(List<Task> tasks,View.OnClickListener clickListener){
        this.tasks = tasks;
        this.clickListener = clickListener;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View taskView = inflater.inflate(R.layout.task_item, parent, false);

        // Return a new holder instance
        ListViewHolder viewHolder = new ListViewHolder(context,taskView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder viewHolder, int position) {
        // Get the data model based on position
        Task task = tasks.get(position);

        // Set item views based on your views and data model
        TextView nameTextView = viewHolder.nameText;
        nameTextView.setText(task.getName());

        TextView descriptionTextView = viewHolder.descriptionText;
        descriptionTextView.setText(task.getDescription());

        LinearLayout background = viewHolder.background;
        String color = task.getPriority();

        switch(color){
            case "Green":
                background.setBackgroundColor(Color.GREEN);
                break;
            case "Yellow":
                background.setBackgroundColor(Color.YELLOW);
                break;
            case "Red":
                background.setBackgroundColor(Color.RED);
                break;
                default:
                    background.setBackgroundColor(Color.WHITE);
        }

        viewHolder.itemView.setTag(task);

        viewHolder.checkBox.setChecked(task.isStatus());
        if(task.isStatus()){
            viewHolder.nameText.setPaintFlags(viewHolder.nameText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.descriptionText.setPaintFlags(viewHolder.nameText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        if(!task.isStatus()){
            viewHolder.nameText.setPaintFlags(viewHolder.nameText.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.descriptionText.setPaintFlags(viewHolder.descriptionText.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }











    }

    @Override
    public int getItemCount(){
        return tasks.size();
    }

    public void addTasks(List<Task> tasks){
        this.tasks = tasks;
        notifyDataSetChanged();
    }






}
