package com.example.android.architecture.blueprints.todoapp.taskdetail

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.filters.MediumTest
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeAndroidTaskRepository
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import com.example.android.architecture.blueprints.todoapp.util.ServiceLocator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class TaskDetailFragmentTest{

    private lateinit var repository:TasksRepository

    @Before
    fun initRepo(){
        repository=FakeAndroidTaskRepository()
        ServiceLocator.tasksRepository=repository
    }
    @After
    fun cleanUp(){
        ServiceLocator.resetRepository()
    }


    @Test
    fun activeTaskDetails_DisplayedInUi() = runBlockingTest {

        val activeTask = Task("Active Task", "AndroidX Rocks", false)
        repository.saveTask(activeTask)

        // WHEN - Details fragment launched to display task
        val bundle = TaskDetailFragmentArgs(activeTask.id).toBundle()
        launchFragmentInContainer<TaskDetailFragment>(bundle, R.style.AppTheme)



}





}