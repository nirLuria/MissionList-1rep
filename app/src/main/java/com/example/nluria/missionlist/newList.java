package com.example.nluria.missionlist;

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

//hi
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
                            Toast.makeText(newList.this, "New list inserted successfully", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(newList.this, "This title name is already exist! " +
                                    "Please choose another name.", Toast.LENGTH_LONG).show();

                        finish();
                    }
                }
        );
    }
}
