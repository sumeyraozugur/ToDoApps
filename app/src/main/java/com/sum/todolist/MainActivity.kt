package com.sum.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sum.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoApp :TodoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        todoApp = TodoAdapter(mutableListOf())

        binding.toDoRecycle.adapter = todoApp
        binding.toDoRecycle.layoutManager =LinearLayoutManager(this)

        binding.buttonAdd.setOnClickListener {
            val todoTitle = binding.enterSomething.text.toString()
            if(todoTitle.isNotEmpty()) {
                val todo = ToDo(todoTitle)  // object from ToDo data class
                todoApp.addTodo(todo)
                binding.enterSomething.text.clear()

            }
        }
        binding.buttonDelete.setOnClickListener {
            todoApp.deleteDoneTodos()
        }



    }
}