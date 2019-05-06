package com.didik.learn.presentation

interface TodoUI {
    interface TodoSaved {
        fun onTodoTitleUpdated(position: Int, title: String)
    }

    interface TodoListener {
        fun onTodoDeleted(position: Int)
    }
}
