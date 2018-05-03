package com.codeclan.balazskertesz.project2;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView nameText;
    public TextView descriptionText;
    public LinearLayout background;
    public Context context;

    public ImageButton editButton;
    public ImageButton deleteButton;

    public CheckBox checkBox;



    public ListViewHolder(Context context,View itemView){
        super(itemView);

        this.context = context;
        nameText = (TextView) itemView.findViewById(R.id.taskNameId);
        descriptionText = (TextView) itemView.findViewById((R.id.taskDescriptionID));
        background = (LinearLayout) itemView.findViewById(R.id.itemBackgroundId);
        editButton = (ImageButton) itemView.findViewById(R.id.editButtonId);
        deleteButton = (ImageButton) itemView.findViewById(R.id.deleteButtonId);
        checkBox = (CheckBox) itemView.findViewById(R.id.checkBoxId);


//        itemView.setOnClickListener(this);
        editButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        checkBox.setOnClickListener(this);



    }

    @Override
    public void onClick(View view){
        int position = getAdapterPosition();

        if(view.getId() == editButton.getId()){
            Toast.makeText(context, "Edit Button for "+nameText.getText(), Toast.LENGTH_SHORT).show();
        }
        else if(view.getId() == deleteButton.getId()){
            Toast.makeText(context,"Delete Button for "+nameText.getText(),Toast.LENGTH_SHORT).show();
            MainActivity mainActivity = (MainActivity) context;
            Task task = mainActivity.viewModel.getTaskList().getValue().get(position);
            mainActivity.viewModel.deleteItem(task);
        }
        else if(view.getId() == checkBox.getId()){
            MainActivity mainActivity = (MainActivity) context;
            Task task = mainActivity.viewModel.getTaskList().getValue().get(position);
            if(task.isStatus()){
                task.setStatus(false);
                mainActivity.viewModel.updateTask(task);
            }
            else{
                task.setStatus(true);
                mainActivity.viewModel.updateTask(task);
            }

        }

//        if(position != RecyclerView.NO_POSITION){
//            Toast.makeText(context, Integer.toString(position), Toast.LENGTH_SHORT).show();
//        }





    }







}
