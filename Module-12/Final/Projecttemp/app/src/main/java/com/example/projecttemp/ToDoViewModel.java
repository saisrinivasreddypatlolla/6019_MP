package com.example.projecttemp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.projecttemp.Database.AppDatabase;
import com.example.projecttemp.Database.TaskEntry;

import java.util.List;

public class ToDoViewModel extends AndroidViewModel {

    // Constant for logging
    private static final String TAG = ToDoViewModel.class.getSimpleName();

    private LiveData<List<TaskEntry>> tasks;

    public ToDoViewModel(Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        Log.d(TAG, "Actively retrieving the tasks from the DataBase");
        tasks = database.taskDao().loadAllTasks();
    }

    public LiveData<List<TaskEntry>> getTasks() {
        return tasks;
    }
}
