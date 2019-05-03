package com.didik.learn.presentation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.didik.learn.R
import com.didik.learn.model.Item

class ItemsAdapter(
    private val itemsList: MutableList<Item>
) : RecyclerView.Adapter<ItemsViewHolder>(), ItemUI.ItemSaved {

    private lateinit var itemListener: ItemUI.ItemListener

    fun setOnItemDeleteClick(listener: ItemUI.ItemListener) {
        itemListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.items_list_layout, parent, false)
        return ItemsViewHolder(view, itemListener, this)
    }

    override fun getItemCount(): Int = itemsList.size

    override fun onBindViewHolder(itemViewHolder: ItemsViewHolder, position: Int) {
        itemViewHolder.bindItem(itemsList[position])
    }

    override fun onItemNameUpdate(position: Int, itemName: String) {
        itemsList[position].name = itemName
    }

    override fun onItemDescriptionUpdate(position: Int, itemDescription: String) {
        itemsList[position].description = itemDescription
    }

}
