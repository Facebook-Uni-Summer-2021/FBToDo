package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
//Android autofill imports wrong FileUtils, replace with following
import org.apache.commons.io.FileUtils;
//import android.os.FileUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
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
    private static final String TAG = "MainActivity";
    List<String> items;
    ItemsAdapter adapter;

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
        //items = new ArrayList<>();
//        items.add("Buy milk");
//        items.add("Pick up children from college");
//        items.add("Do some coding");
        loadItems();

        //Remove an item based on position of item in ItemsAdapter
        //It seems we created an interface in the adapter to communicate
        //with the class that initializes the recyclerview, since it
        //is here where we also initialize the list of items and it
        //is here where we need to change the list based on what item
        //was click in adapter
        ItemsAdapter.OnLongClickListener onLongClickListener =
                new ItemsAdapter.OnLongClickListener() {
            @Override
            public void onItemLongClicked(int position) {
                //Delete item from model
                items.remove(position);
                //Notify adapter
                adapter.notifyItemRemoved(position);
                Toast.makeText(
                        getApplicationContext(),
                        "Item was removed",
                        Toast.LENGTH_SHORT).show();
                saveItems();
            }
        };

        //Complete steps to create adapter as seen in ItemsAdapter
        adapter = new ItemsAdapter(items, onLongClickListener);
        rvItems.setAdapter(adapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));

        //Create handler for adding items (let autofill help)
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get text from etItem and create new item
                //Ensure conversion to string
                String toDoItem = etItem.getText().toString();
                //Add item to model
                items.add(toDoItem);
                //Notify adapter of change
                //adapter.notifyDataSetChanged();//Does this work? Requires more energy (time?),
                //so use when knowledge of adding/changing is not known
                adapter.notifyItemInserted(items.size() - 1);
                etItem.setText("");
                Toast.makeText(
                        getApplicationContext(),
                        "Item was added",
                        Toast.LENGTH_SHORT).show();//MAKE SURE TO SHOW!!! AHHHHHHHHHHHHH
                        saveItems();
            }
        });
    }

    //Create series of methods for controlling files and keeping persistence

    //Return file list of items is stored
    private File getDataFile () {
        //Current directory, followed by name of file
        return new File(getFilesDir(), "data.txt");
    }

    //Load items by reading data file; occurs when app boots
    private void loadItems () {
        try {
            items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch (IOException e) {
            //Handle error if no file is found, which can occur
            //upon first time running since no file exists
            Log.e(TAG, "Error loading items: " + e);
            items = new ArrayList<>();
        }
    }

    //Save items by writing data file; occurs when
    //change happens to list (add and remove)
    private void saveItems () {
        try {
            FileUtils.writeLines(getDataFile(), items);
        } catch (IOException e) {
            Log.e(TAG, "Error writing items: " + e);
        }
    }
}