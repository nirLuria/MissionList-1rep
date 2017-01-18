package com.example.nluria.missionlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class tasks extends AppCompatActivity
{
    String nameOfGroup;
    Button btnAddTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        Intent intent = getIntent();
        nameOfGroup= intent.getStringExtra("name");
        TextView title= (TextView) findViewById(R.id.title);
        title.setText(nameOfGroup);

        btnAddTask = (Button)findViewById(R.id.Add_btn);
        addNewTask();

    }

    public void addNewTask()
    {
        btnAddTask.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        System.out.println(nameOfGroup);


                    }
                }
        );
    }


}
