package com.sum.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sum.todolist.databinding.ItemTodoBinding

class TodoAdapter(private val todoList: MutableList<ToDo>):RecyclerView.Adapter<TodoAdapter.ToDoViewHolder>() {

    class ToDoViewHolder(val binding:ItemTodoBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding =ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ToDoViewHolder(binding)
    }

    fun addTodo(todo: ToDo) {
        todoList.add(todo)
        notifyItemInserted(todoList.size-1 )

    }

    fun deleteDoneTodos() {
        todoList.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvTodoTitle.paintFlags= tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG //üstünü çizer and first paintFlags from set the other one is from get
            println("checked")

        } else {
           tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv() // üst çizmeyi geri alır
            println("not checked")

        }
    }





    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
         val curTodo = todoList[position]
        holder.itemView.apply {
            holder.binding.toDoTextView.text = curTodo.title
            holder.binding.toDoCheckBox.isChecked  = curTodo.isChecked

            toggleStrikeThrough(holder.binding.toDoTextView, curTodo.isChecked)
            holder.binding.toDoCheckBox.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(holder.binding.toDoTextView, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }

    }

    override fun getItemCount(): Int {
        return todoList.size

    }
}