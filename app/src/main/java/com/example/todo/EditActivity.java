package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    //Create variable objects of activity_edit widgets
    //While following tutorial, got conflicts with name of widgets; ensure correct names are used
    EditText etUpdate;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etUpdate = findViewById(R.id.etUpdate);
        btnSave = findViewById(R.id.btnSave);

        getSupportActionBar().setTitle("Edit Item");

        //Get data from new activity/intent
        etUpdate.setText(getIntent().getStringExtra(MainActivity.KEY_ITEM_TEXT));

        //Save updated item
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create intent to contain modified data
                Intent intent = new Intent();
                //Pass data
                //New text to update item
                intent.putExtra(
                        MainActivity.KEY_ITEM_TEXT, etUpdate.getText().toString());
                //Original position of item in list
                intent.putExtra(
                        MainActivity.KEY_ITEM_POSITION,
                        getIntent().getExtras().getInt(MainActivity.KEY_ITEM_POSITION));
                //Set result
                setResult(RESULT_OK, intent);
                //End activity
                finish();
            }
        });
    }
}