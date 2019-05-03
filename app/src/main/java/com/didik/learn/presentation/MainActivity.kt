package com.didik.learn.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.didik.learn.R
import com.didik.learn.model.Item
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var itemList: MutableList<Item> = mutableListOf()
    private lateinit var itemsAdapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpLayout()
    }

    private fun setUpLayout() {
        itemList.add(Item("",  ""))
        items_recycler_view.layoutManager = LinearLayoutManager(this)
        itemsAdapter = ItemsAdapter(itemList)
        items_recycler_view.adapter = itemsAdapter

        itemsAdapter.setOnItemDeleteClick(object: ItemUI.ItemListener {
            override fun onItemDeleted(position: Int) {
                itemList.removeAt(position)
                itemsAdapter.notifyDataSetChanged()
            }
        })

        btn_add_item.setOnClickListener {
            val position = if (itemList.isEmpty()) 0 else itemList.size - 1
            itemList.add(Item("", ""))
            itemsAdapter.notifyItemInserted(position)
            itemsAdapter.notifyDataSetChanged()
        }

        btn_clear_item.setOnClickListener {
            itemList.clear()
            itemsAdapter.notifyDataSetChanged()
        }
    }
}
