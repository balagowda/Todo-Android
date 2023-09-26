package com.example.mytask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.mytask.Adopter.TodoAdopter;
import com.example.mytask.Model.ToDoModel;
import com.example.mytask.Utils.Databasehandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogCloseListener{

    private RecyclerView taskRecyclerView;
    private TodoAdopter taskAdopter;
    private List<ToDoModel> taskList;
    private Databasehandler db;
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
        taskAdopter = new TodoAdopter(this);
        taskRecyclerView.setAdapter(taskAdopter);

        ToDoModel task = new ToDoModel();
        task.setTask("This is a task list");
        task.setStatus(0);
        task.setId(1);

        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);

        taskAdopter.setTask(taskList);
    }

    @Override
    public void handleDialogClose(DialogInterface dialog){
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        taskAdopter.setTask(taskList);
        taskAdopter.notifyDataSetChanged();
    }
}