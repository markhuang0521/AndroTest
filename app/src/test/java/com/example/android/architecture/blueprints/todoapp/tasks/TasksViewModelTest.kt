package com.example.android.architecture.blueprints.todoapp.tasks

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTaskRepository
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.P])
class TasksViewModelTest{
    private lateinit var viewModel: TasksViewModel
    private lateinit var fakeRepo: FakeTaskRepository

    @Before
    fun setupViewModel() {
        fakeRepo= FakeTaskRepository()
        val task1 = Task("Title1", "Description1")
        val task2 = Task("Title2", "Description2", true)
        val task3 = Task("Title3", "Description3", true)
        fakeRepo.addtask(task1,task2,task3)
        viewModel = TasksViewModel(fakeRepo)
    }

    @get:Rule
    var instantExcutorRule=InstantTaskExecutorRule()


    @Test
    fun addNewTask_setNewTaskEvent(){

        viewModel.addNewTask()
        val value = viewModel.newTaskEvent.getOrAwaitValue()
        assertThat(value.getContentIfNotHandled(), (not(nullValue())))

    }
    @Test
    fun setFilterAllTasks_tasksAddViewVisible(){
        viewModel.setFiltering(TasksFilterType.ALL_TASKS)
        assertThat(viewModel.tasksAddViewVisible.getOrAwaitValue(), `is`(true))

    }
}