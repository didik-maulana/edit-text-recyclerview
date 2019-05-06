package com.didik.learn.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.didik.learn.R
import com.didik.learn.model.Todo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var todoList: MutableList<Todo> = mutableListOf()
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpLayout()
    }

    private fun setUpLayout() {
        todoList.add(Todo(""))
        todo_recycler_view.layoutManager = LinearLayoutManager(this)
        todoAdapter = TodoAdapter(todoList)
        todo_recycler_view.adapter = todoAdapter

        todoAdapter.setOnTodoDeleteClick(object : TodoUI.TodoListener {
            override fun onTodoDeleted(position: Int) {
                todoList.removeAt(position)
                todoAdapter.notifyDataSetChanged()
            }
        })

        btn_add_todo.setOnClickListener {
            val position = if (todoList.isEmpty()) 0 else todoList.size - 1
            todoList.add(Todo(""))
            todoAdapter.notifyItemInserted(position)
            todoAdapter.notifyDataSetChanged()
        }

        btn_clear_todo.setOnClickListener {
            todoList.clear()
            todoList.add(Todo(""))
            todoAdapter.notifyDataSetChanged()
        }
    }
}
