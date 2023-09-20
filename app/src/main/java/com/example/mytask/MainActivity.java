package com.example.mytask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mytask.Adopter.TodoAdopter;
import com.example.mytask.Model.ToDoModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView taskRecyclerView;
    private TodoAdopter taskAdopter;
    private List<ToDoModel> taskList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

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


    }
}