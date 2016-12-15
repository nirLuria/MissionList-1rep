package com.example.nluria.missionlist;

import android.annotation.SuppressLint;
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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class viewGroups extends AppCompatActivity
{
    private static ListView listView;
    List<String> groupsArray = new ArrayList<String>();
    private static Button view_groups_button;
    private static Button delete_groups_button;
    DataBaseHelper myDb;

    private String stringToDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_groups);
        myDb = new DataBaseHelper(this);

        groupsView();
        deleteAllGroupsClickListener();
        //  viewGroupsClickListener();
     //   viewGroupsByOpenedWin();
    }



    public void groupsView()
    {
        Cursor res = myDb.getGroups();
        if (res.getCount()==0)
        {
            //no data.
            showMessage("Mmmmm... ", "There is no any group yet.");
          //  finish();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        int number=1;

        //  ###print to screen the database data.        ###
        while (res.moveToNext())
        {
            buffer.append(number+". " + res.getString(1) + "\n");
            System.out.println(res.getString(1));
            groupsArray.add(res.getString(1));
            number++;
        }
        //  ###                                         ###

        listView = (ListView)findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_of_groups, groupsArray );
        listView.setAdapter(adapter);

        //show tasks of group.
        listView.setOnItemClickListener
        (
                new AdapterView.OnItemClickListener()
                {
                      @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                    {
                        Intent intent = new Intent("com.example.nluria.missionlist.tasks");
                        startActivity(intent);
                    }
                }
        );
    }



    /*


     */



    //delete all groups.
    public void deleteAllGroupsClickListener()
    {

        delete_groups_button = (Button)findViewById(R.id.Delete_all_groupsBtn);
        delete_groups_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert_builder = new AlertDialog.Builder(viewGroups.this);
                alert_builder.setMessage("Do you realy want to delete all groups?")
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
                                //delete all groups.
                                boolean isDeleted = myDb.deleteAllGroups();
                                if (isDeleted = true)
                                    Toast.makeText(viewGroups.this, "All groups were deleted successfully", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(viewGroups.this, "error when deleting", Toast.LENGTH_LONG).show();

                                finish();
                            }
                        });
                AlertDialog alert = alert_builder.create();
                alert.setTitle("Delete all?");
                alert.show();
            }
        });
    }




/*
//when user press - delete group.
        listView.setOnItemClickListener
        (
                new AdapterView.OnItemClickListener()
                {
                   //delete group

                      @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                    {
                        /*
                        //String value = (String)listView.getItemAtPosition(position);
                        stringToDelete= (String)listView.getItemAtPosition(position);
                        // Toast.makeText(viewGroups.this, "i want to delete " + value + ", position: " + position,
                        //       Toast.LENGTH_LONG).show();

                        AlertDialog.Builder alert_builder = new AlertDialog.Builder(viewGroups.this);
                        alert_builder.setMessage("Do you want to delete this group?")
                                .setCancelable(false)
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                })
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i)
                                    {
                                        //delete group from database, send to user success message, and go back to main menu.
                                        boolean isInserted = myDb.deleteList(stringToDelete);
                                        if (isInserted = true)
                                            Toast.makeText(viewGroups.this, stringToDelete +" was deleted successfully", Toast.LENGTH_LONG).show();
                                        else
                                            Toast.makeText(viewGroups.this, stringToDelete + " was not deleted", Toast.LENGTH_LONG).show();

                                        finish();
                                    }
                                });
                        AlertDialog alert = alert_builder.create();
                        alert.setTitle("Delete?");
                        alert.show();


}
}



        );

 */






    //show the data in an openned windows.  for now - not used!!!!!
    public void viewGroupsClickListener()
    {

           //view_groups_button= (Button)findViewById(R.id.View_groupsButton);
        view_groups_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.nluria.missionlist.viewGroups");
                startActivity(intent);
            }
        });
    }



    //show the data in an openned windows.  for now - not used!!!!!
    //print to screen the groups.
    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }


    //show the data in an openned windows.  for now - not used!!!!!
    //save in a buffer all the groups of tasks. execute showMessage.
    public void viewGroupsByOpenedWin()
    {
        view_groups_button.setOnClickListener
                (
                        new View.OnClickListener()
                        {

                            @SuppressLint("NewApi")
                            @Override
                            public void onClick(View v)
                            {
                                Cursor res = myDb.getGroups();
                                if (res.getCount()==0)
                                {
                                    //no data.
                                    showMessage("Error:", "no data");
                                    return;
                                }
                                StringBuffer buffer = new StringBuffer();
                                int number=1;

                                //  ###print to screen the database data.        ###
                                while (res.moveToNext())
                                {
                                    buffer.append(number+". " + res.getString(1) + "\n");
                                    System.out.println(res.getString(1));
                                    number++;
                                }
                                //  ###                                         ###

                                showMessage("My currently groups:", buffer.toString());
                            }

                        }
                );
    }

}