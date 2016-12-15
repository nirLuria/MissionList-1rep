package com.example.nluria.missionlist;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

        ActionBar ab = getSupportActionBar();
        ab.setLogo(R.drawable.logo);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);         //   <- option
        ab.setDisplayShowHomeEnabled(true);

        exitButtonClickListener();
        newGroupClickListener();
        viewGroupsClickListener();

        myDb = new DataBaseHelper(this);


    }


    //displaying options menu bar.
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //the menu bar itself.
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.info_id:
                Toast.makeText(getApplicationContext(), "info icon is selected", Toast.LENGTH_SHORT).show();
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(), "setting icon is selected", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }
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
