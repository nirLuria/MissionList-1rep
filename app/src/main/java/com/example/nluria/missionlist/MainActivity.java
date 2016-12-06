package com.example.nluria.missionlist;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDb;


    private static Button exit_button;
    private static Button view_groups_button;
    private static Button new_list_button;

  //  private static Button newView_groups_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exitButtonClickListener();

    //    view_groups_button= (Button)findViewById(R.id.View_groupsButton);

        newGroupClickListener();

        viewGroupsClickListener();

        myDb = new DataBaseHelper(this);

      //  viewAll();
    }




    //exit.
    public void exitButtonClickListener()
    {

        exit_button = (Button)findViewById(R.id.exitBotton);
        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert_builder = new AlertDialog.Builder(MainActivity.this);
                alert_builder.setMessage("Do you want to exit?")
                        .setCancelable(false)
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });
                AlertDialog alert = alert_builder.create();
                alert.setTitle("Exit?");
                alert.show();
            }
        });
    }


/*
    //save in a buffer all the groups of tasks. execute showMessage.
    public void viewAll()
    {
        view_groups_button.setOnClickListener
        (
                new View.OnClickListener()
                {
                    @SuppressLint("NewApi")
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getGroups();
                        if (res.getCount()==0)
                        {
                            //no data.
                            showMessage("Error:", "no data");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        int number=1;
                        while (res.moveToNext())
                        {
                            buffer.append(number+". " + res.getString(1) + "\n");
                            number++;
                        }

                        showMessage("My currently groups:", buffer.toString());
                    }

                }
        );
    }
*/

    //print to screen the groups.
    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

     }

    public void newGroupClickListener()
    {
        new_list_button= (Button)findViewById(R.id.New_ListButton);
        new_list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.nluria.missionlist.newList");
                startActivity(intent);
            }
        });
    }



    public void viewGroupsClickListener()
    {
        view_groups_button = (Button)findViewById(R.id.newViewGroupsButton);
        view_groups_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent("com.example.nluria.missionlist.viewGroups");
                        startActivity(intent);
                    }
                }
        );
    }



}
