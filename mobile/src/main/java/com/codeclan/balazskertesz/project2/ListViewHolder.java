package com.codeclan.balazskertesz.project2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListViewHolder extends RecyclerView.ViewHolder {

    public TextView nameText;
    public TextView descriptionText;
    public LinearLayout background;

    public ListViewHolder(View itemView){
        super(itemView);

        nameText = (TextView) itemView.findViewById(R.id.taskNameId);
        descriptionText = (TextView) itemView.findViewById((R.id.taskDescriptionID));
        background = (LinearLayout) itemView.findViewById(R.id.itemBackgroundId);
    }
}
