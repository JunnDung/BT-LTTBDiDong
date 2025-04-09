package com.example.baitaptuan3_bai2.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.baitaptuan3_bai2.model.Task

class TaskViewModel : ViewModel() {
    private val _tasks = mutableStateListOf<Task>()
    val tasks: List<Task> = _tasks

    init {
        addSampleTasks()
    }
    
    private fun addSampleTasks() {
        addTask(
            Task(
                title = "Complete Android Project",
                description = "Finish the UI, integrate API, and write documentation",
                color = 0xFF90CAF9
            )
        )
        
        addTask(
            Task(
                title = "Complete Android Project",
                description = "Finish the UI, integrate API, and write documentation",
                color = 0xFFF8BBD0
            )
        )
        
        addTask(
            Task(
                title = "Complete Android Project",
                description = "Finish the UI, integrate API, and write documentation",
                color = 0xFFDCEDC8
            )
        )
        
        addTask(
            Task(
                title = "Complete Android Project",
                description = "Finish the UI, integrate API, and write documentation",
                color = 0xFFF8BBD0
            )
        )
    }
    
    fun addTask(task: Task) {
        _tasks.add(task)
    }
    
    fun removeTask(taskId: String) {
        _tasks.removeIf { it.id == taskId }
    }
} 