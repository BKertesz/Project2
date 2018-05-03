package com.codeclan.balazskertesz.project2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

//This is the hearth and soul of the recyclerView the adapter
//Took me soo long to understand and write it
public class MyRecyclerAdapter extends RecyclerView.Adapter<ListViewHolder> {

    private List<Task> tasks;
    private Context context;

    public MyRecyclerAdapter(List<Task> tasks){
        this.tasks = tasks;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Context is pulled soo the ListViewHolder can reference it
        //Used to be able to deleted, and update objects
        context = parent.getContext();

        //Creates a new Inflater object
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View taskView = inflater.inflate(R.layout.task_item, parent, false);

        // This is the bit where the ViewHolder gets populated
        ListViewHolder viewHolder = new ListViewHolder(context,taskView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder viewHolder, int position) {
        // Get the Task object based on position
        Task task = tasks.get(position);

        // Set name Text View to task's name
        TextView nameTextView = viewHolder.nameText;
        nameTextView.setText(task.getName());

        //Set description for the task
        TextView descriptionTextView = viewHolder.descriptionText;
        descriptionTextView.setText(task.getDescription());

        //This last bit is used to color the background of each row invidually
        //Each task has a string of priority then this get checked by the switch
        LinearLayout background = viewHolder.background;

        Resources res = context.getResources();


        switch(task.getPriority()){
            case "Green":
                background.setBackgroundColor(res.getColor(R.color.greenPriority));
                break;
            case "Yellow":
                background.setBackgroundColor(res.getColor(R.color.yellowPriority));
                break;
            case "Red":
                background.setBackgroundColor(res.getColor(R.color.redPriority));
                break;
                default:
                    background.setBackgroundColor(Color.WHITE);
        }

        //Unused, items can be tagged here by they data
        //viewHolder.itemView.setTag(task);

        //This bit set up the checkbox based on the task
        viewHolder.checkBox.setChecked(task.isStatus());

        //Checks if the task's status is true or false, soo is it finished or not
        if(task.isStatus()){
            //If it is true then it paints it over with strikethrough
            // The | symbol here is not or, its a bitwise operator of shift
            viewHolder.nameText.setPaintFlags(viewHolder.nameText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.descriptionText.setPaintFlags(viewHolder.nameText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        if(!task.isStatus()){
            //This is the reversing effect when a checkbox is unchecked
            // Here the & is a bitwise operator aswell not an and
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
