package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}