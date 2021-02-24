package com.example.android.architecture.blueprints.todoapp

import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.statistics.getActiveAndCompletedStats
import org.hamcrest.core.Is.`is`
import org.junit.Assert.*
import org.junit.Test

class StatisticsUtilsTest {

    @Test
    fun getActiveAndCompletedStats_empty_returnZero(){
        val tasks= emptyList<Task>()

        val result= getActiveAndCompletedStats(tasks)
        assertThat(result.activeTasksPercent,`is`(0f))
        assertThat(result.completedTasksPercent,`is`(0f))
//        assertEquals(0f,result.activeTasksPercent)
//        assertEquals(0f,result.completedTasksPercent)

    }
    @Test
    fun getActiveAndCompletedStats_active_returnFortyHundred(){
        val tasks= listOf<Task>(
                Task("title","desc",false),
                Task("title","desc",false),
                Task("title","desc",true),
                Task("title","desc",true),
                Task("title","desc",true)
        )

        val result= getActiveAndCompletedStats(tasks)
        assertThat(result.activeTasksPercent,`is`(40f))
        assertThat(result.completedTasksPercent,`is`(60f))
//        assertEquals(0f,result.activeTasksPercent)
//        assertEquals(0f,result.completedTasksPercent)

    }
    @Test
    fun getActiveAndCompletedStats_error_returnNull(){
        val tasks= null

        val result= getActiveAndCompletedStats(tasks)
        assertEquals(0f,result.activeTasksPercent)
        assertEquals(0f,result.completedTasksPercent)

    }


}