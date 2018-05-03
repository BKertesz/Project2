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

//This class is used to the RecyclerView, it is used to define the look and context
// Of each invidual row created with the Recycler

public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    //References to visual objects
    public TextView nameText;
    public TextView descriptionText;
    public LinearLayout background;
    public ImageButton editButton;
    public ImageButton deleteButton;
    public CheckBox checkBox;

    //This is the main context that happening, which is the Main Activity
    //Used for reference and manipulate the main activity
    public Context context;





    public ListViewHolder(Context context,View itemView){
        super(itemView);
        //Constructor which run every time a row is recreated

        //This is important to store soo each elemnt can refernce it later
        this.context = context;


        nameText = (TextView) itemView.findViewById(R.id.taskNameId);
        descriptionText = (TextView) itemView.findViewById((R.id.taskDescriptionID));
        background = (LinearLayout) itemView.findViewById(R.id.itemBackgroundId);
        editButton = (ImageButton) itemView.findViewById(R.id.editButtonId);
        deleteButton = (ImageButton) itemView.findViewById(R.id.deleteButtonId);
        checkBox = (CheckBox) itemView.findViewById(R.id.checkBoxId);


        //Unused methods where you can get the row to be clickable or the edit button
        //itemView.setOnClickListener(this);
        //editButton.setOnClickListener(this);

        //Set up the on click listener for every button on the invidual rows
        deleteButton.setOnClickListener(this);
        checkBox.setOnClickListener(this);



    }

    @Override
    public void onClick(View view){
        //This is the method responsible to recognize which button is pressed

        //Each row knows it position by requestiong it from the adapter
        int position = getAdapterPosition();


        //Here is checks if the edit button is checked, unused at the moment
        if(view.getId() == editButton.getId()){
            Toast.makeText(context, "Edit Button for "+nameText.getText(), Toast.LENGTH_SHORT).show();
        }
        //Checks if the button pressed is the delete button for this row
        else if(view.getId() == deleteButton.getId()){
            //First gives a confirmation toast that the task is deleted
            Toast.makeText(context,"Deleted "+nameText.getText(),Toast.LENGTH_SHORT).show();

            //From the context recreate the main activity
            MainActivity mainActivity = (MainActivity) context;

            //Recreate the task the following way:
            //1. From main activity find the database access
            //2. From database get LiveData list
            //3. From the LiveData get the task list
            //4. From the list find the task that has the same position as this row
            //5. Capture that task in a new disposable task
            Task task = mainActivity.viewModel.getTaskList().getValue().get(position);

            //Call Main Activitys access to the database and pass in the reference to the
            //Task we created earlier, then the DAO does the rest
            mainActivity.viewModel.deleteItem(task);
        }
        else if(view.getId() == checkBox.getId()){
            //This one checks if the view thats been clicked is the checkbox
            //Like the delete we find out our main activity
            //And find the task that references this row
            MainActivity mainActivity = (MainActivity) context;
            Task task = mainActivity.viewModel.getTaskList().getValue().get(position);

            //Check if the task is already ticked as in true and set it to false
            //Then update the task in the database
            if(task.isStatus()){
                task.setStatus(false);
                mainActivity.viewModel.updateTask(task);
            }
            else{
                //If not true then change it to true
                //Also if the system is bugged it turns it into true anyway
                task.setStatus(true);
                mainActivity.viewModel.updateTask(task);
            }

        }

        //Unused test method, used for checking if a row is checked and return its position
//        if(position != RecyclerView.NO_POSITION){
//            Toast.makeText(context, Integer.toString(position), Toast.LENGTH_SHORT).show();
//        }





    }







}
