package com.example.nluria.missionlist;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class newList extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText title;
    Button btnAddList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);
        myDb = new DataBaseHelper(this);

        title = (EditText)findViewById(R.id.add_a_title);
        btnAddList = (Button)findViewById(R.id.addNewListBtn);
        addNewList();
    }

//test
    public void addNewList()
    {
        btnAddList.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        boolean isInserted = myDb.insertNewList(title.getText().toString());
                        System.out.println(" isInserted is: " + isInserted);

                        if (isInserted == true)
                        {
                            Toast.makeText(newList.this, "New list inserted successfully", Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else
                        {
                            AlertDialog.Builder alert_builder = new AlertDialog.Builder(newList.this);
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

                        }
                        //    Toast.makeText(newList.this, "This title name is already exist! " +
                          //          "Please choose another name.", Toast.LENGTH_LONG).show();


                    }
                }
        );
    }
}
