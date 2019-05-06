package com.didik.learn.presentation

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.didik.learn.R
import com.didik.learn.model.Todo
import kotlinx.android.synthetic.main.todo_list_layout.view.*

class TodoViewHolder(
    view: View,
    todoListener: TodoUI.TodoListener,
    todoSaved: TodoUI.TodoSaved
) : RecyclerView.ViewHolder(view) {

    private val todoNumber: TextView = view.todo_number_label
    private val todoTitle: EditText = view.input_todo_title
    private val iconDeleteTodo: ImageView = view.icon_delete_todo

    init {
        iconDeleteTodo.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                todoListener.onTodoDeleted(adapterPosition)
            }
        }

        todoTitle.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val name = s.toString()

                todoSaved.run {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        todoSaved.onTodoTitleUpdated(adapterPosition, name)
                    }
                }
            }
        })
    }

    fun bindTodo(todo: Todo) {
        todoNumber.text = String.format(
            itemView.context
                .getString(R.string.todo_number), adapterPosition + 1
        )

        todoTitle.setText(todo.title)

        iconDeleteTodo.visibility = if (adapterPosition == 0) View.GONE
        else View.VISIBLE
    }
}
