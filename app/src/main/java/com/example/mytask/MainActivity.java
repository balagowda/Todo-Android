package com.example.mytask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.mytask.Adopter.TodoAdopter;
import com.example.mytask.Model.ToDoModel;
import com.example.mytask.Utils.Databasehandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogCloseListener{

    private RecyclerView taskRecyclerView;
    private TodoAdopter taskAdopter;
    private List<ToDoModel> taskList;
    private Databasehandler db;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().hide();

        db = new Databasehandler(this);
        db.openDatabase();

        taskList = new ArrayList<>();

        taskRecyclerView = findViewById(R.id.tasksRecyclerView);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdopter = new TodoAdopter(db,MainActivity.this);
        taskRecyclerView.setAdapter(taskAdopter);

        fab = findViewById(R.id.fab);

        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        taskAdopter.setTasks(taskList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewTask.newInstance().show(getSupportFragmentManager(),AddNewTask.TAG);
            }
        });
    }

    @Override
    public void handleDialogClose(DialogInterface dialog){
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        taskAdopter.setTasks(taskList);
        taskAdopter.notifyDataSetChanged();
    }
}