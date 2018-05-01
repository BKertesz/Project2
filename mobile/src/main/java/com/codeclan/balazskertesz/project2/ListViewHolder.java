package com.codeclan.balazskertesz.project2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView nameText;
    public TextView descriptionText;
    public LinearLayout background;
    public Context context;



    public ListViewHolder(Context context,View itemView){
        super(itemView);


        nameText = (TextView) itemView.findViewById(R.id.taskNameId);
        descriptionText = (TextView) itemView.findViewById((R.id.taskDescriptionID));
        background = (LinearLayout) itemView.findViewById(R.id.itemBackgroundId);

        this.context = context;
        itemView.setOnClickListener(this);


    }

    @Override
    public void onClick(View view){
        int position = getAdapterPosition();
        if(position != RecyclerView.NO_POSITION){
//            User user = users.get(position);
            Toast.makeText(context, nameText.getText(), Toast.LENGTH_SHORT).show();
        }
    }


}
