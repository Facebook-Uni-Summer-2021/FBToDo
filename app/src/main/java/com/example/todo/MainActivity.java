package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*
    Goals of this app:
    View a list of existing items
    Add new items
    Remove existing items

    Standard requirements of app can be CRUD:
    Create
    Read
    Update
    Delete

    Model-View-Controller (MVC Paradigm)
    Model - structure of data
        Items are strings
    View - user interface
        List of items, controls for CRUD
    Controller - behavior of the above
        How to wire model to view (rendering),
        load/save model (persistence),
        add/remove model items (mutation)

    Wireframe - create rough draft for view, include info of what
    controls perform what actions

    View is defined by resources (layout)
    Model/Controller in Java
    Persistence via device file system
    User actions handled by handlers (way Android informs of interaction by user)

    NEW Goals of app
    Construct user interface
        Display items, button to add items
    Implement model as list of String
        Mock data?
    Implement mutation actions
        Add on press, remove on press-and-hold
    Implement persistence
        Load model from file system on app startup
        Store to file system after changes

    Search "CodePath Android guide"
     */
    List<String> items;

    //Create respective object for each widget used in XML
    Button btnAdd;
    EditText etItem;
    RecyclerView rvItems;

    /**
     * Signifies functions that run as activity is started. The main
     * activity/class needs to be defined as such in Android Manifest
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize/define member variables
        btnAdd = findViewById(R.id.btnAdd);
        etItem = findViewById(R.id.etItem);
        rvItems = findViewById(R.id.rvItems);

        //Initialize list for items, including mock data
        items = new ArrayList<>();
        items.add("Buy milk");
        items.add("Pick up children from college");
        items.add("Do some coding");

        //Complete steps to create adapter as seen in ItemsAdapter
        ItemsAdapter adapter = new ItemsAdapter(items);
        rvItems.setAdapter(adapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
    }
}