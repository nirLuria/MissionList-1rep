package com.example.nluria.missionlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class tasks extends AppCompatActivity
{
    DataBaseHelper myDb;
    String nameOfGroup;
    Button btnAddTask;
    EditText input;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        myDb = new DataBaseHelper(this);

        //print title of group on screen.
        Intent intent = getIntent();
        nameOfGroup= intent.getStringExtra("name");
        TextView title= (TextView) findViewById(R.id.title);
        title.setText(nameOfGroup);

        addNewTask();
        groupsView();
    }

    public void addNewTask()
    {
        //create alert dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add a new task:");
        builder.setMessage("");
        input= new EditText(this);
        builder.setView(input);

        //set negative button.
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        //set positive button.
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //String txt = input.getText().toString();
                //Toast.makeText(getApplicationContext(),txt, Toast.LENGTH_LONG).show();

                boolean isInserted = myDb.insertNewTask(input.getText().toString(),nameOfGroup);
                System.out.println(" isInserted is: " + isInserted);

                if (isInserted == true)
                {
                    Toast.makeText(tasks.this, "New task inserted successfully", Toast.LENGTH_LONG).show();
        //            finish();
                }
                else
                {
                    Toast.makeText(tasks.this, "Baddddddd", Toast.LENGTH_LONG).show();
              ///      finish();
                    /*
                    AlertDialog.Builder alert_builder = new AlertDialog.Builder(tasks.this);
                    alert_builder.setMessage("Please choose another name.")
                            .setCancelable(false)
                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    AlertDialog alert = alert_builder.create();
                    alert.setTitle("This title name is already exists!");
                    alert.show();
                    */

                }
                //    Toast.makeText(newList.this, "This title name is already exist! " +
                //          "Please choose another name.", Toast.LENGTH_LONG).show();



                input.setText("");
            }
        });


        final AlertDialog alertDialog= builder.create();


        btnAddTask= (Button)findViewById(R.id.Add_btn);
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();
            }
        });
    }



    public void groupsView()
    {
        Cursor res = myDb.getTasks(nameOfGroup);
        if (res.getCount()==0)
        {
            System.out.println(" no tasks");

        }
        else
        {
            System.out.println(" i have tasks!");

        }
    }


}
