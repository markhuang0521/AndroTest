package com.example.android.architecture.blueprints.todoapp.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.architecture.blueprints.todoapp.data.Result
import com.example.android.architecture.blueprints.todoapp.data.Task
import kotlinx.coroutines.runBlocking

class FakeTaskRepository :TasksRepository {

    var tasksServiceData:LinkedHashMap<String,Task> = LinkedHashMap()
    private val observableTasks = MutableLiveData<Result<List<Task>>>()
    private var shouldReturnError = false

    fun addtask(vararg  tasks:Task){
        for(task:Task in tasks){
            tasksServiceData[task.id]=task
        }
        runBlocking { refreshTasks() }
    }

    override suspend fun getTasks(forceUpdate: Boolean): Result<List<Task>> {
return Result.Success(tasksServiceData.values.toList())

    }

    override suspend fun refreshTasks() {
        observableTasks.value=getTasks()


    }

    override fun observeTasks(): LiveData<Result<List<Task>>> {
        runBlocking {
            refreshTasks()
        }
        return observableTasks
    }

    override suspend fun refreshTask(taskId: String) {
        observableTasks.value
    }

    override fun observeTask(taskId: String): LiveData<Result<Task>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTask(taskId: String, forceUpdate: Boolean): Result<Task> {
        if (shouldReturnError) {
            return Result.Error(Exception("Test exception"))
        }
        tasksServiceData[taskId]?.let {
            return Result.Success(it)
        }
        return Result.Error(Exception("Could not find task"))    }

    override suspend fun saveTask(task: Task) {
        tasksServiceData[task.id]=task
    }

    override suspend fun completeTask(task: Task) {
        val completedTask = Task(task.title, task.description, true, task.id)
        tasksServiceData[task.id] = completedTask    }

    override suspend fun completeTask(taskId: String) {
        }

    override suspend fun activateTask(task: Task) {
        val completedTask = Task(task.title, task.description, false, task.id)
        tasksServiceData[task.id] = completedTask
    }

    override suspend fun activateTask(taskId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun clearCompletedTasks() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllTasks() {
        tasksServiceData.clear()
    }

    override suspend fun deleteTask(taskId: String) {
        TODO("Not yet implemented")
    }
}