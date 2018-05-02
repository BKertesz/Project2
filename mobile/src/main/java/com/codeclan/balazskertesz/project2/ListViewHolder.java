package com.codeclan.balazskertesz.project2;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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



    public ListViewHolder(Context context,View itemView){
        super(itemView);

        this.context = context;
        nameText = (TextView) itemView.findViewById(R.id.taskNameId);
        descriptionText = (TextView) itemView.findViewById((R.id.taskDescriptionID));
        background = (LinearLayout) itemView.findViewById(R.id.itemBackgroundId);
        editButton = (ImageButton) itemView.findViewById(R.id.editButtonId);
        deleteButton = (ImageButton) itemView.findViewById(R.id.deleteButtonId);


//        itemView.setOnClickListener(this);
        editButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View view){
        int position = getAdapterPosition();

        if(view.getId() == editButton.getId()){
            Toast.makeText(context, "Edit Button for "+nameText.getText(), Toast.LENGTH_SHORT).show();
        }
        else if(view.getId() == deleteButton.getId()){
            Toast.makeText(context,"Delete Button for "+nameText.getText(),Toast.LENGTH_SHORT).show();
        }

//        if(position != RecyclerView.NO_POSITION){
//            Toast.makeText(context, Integer.toString(position), Toast.LENGTH_SHORT).show();
//        }





    }







}
