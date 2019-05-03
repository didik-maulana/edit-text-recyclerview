package com.didik.learn.presentation

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.didik.learn.R
import com.didik.learn.model.Item
import kotlinx.android.synthetic.main.items_list_layout.view.*

class ItemsViewHolder(
    view: View,
    itemListener: ItemUI.ItemListener,
    itemSaved: ItemUI.ItemSaved
) : RecyclerView.ViewHolder(view) {

    private val itemNumber: TextView = view.item_number_label
    private val itemName: EditText = view.input_item_name
    private val itemDescription: EditText = view.input_item_description
    private val iconItemDelete: ImageView = view.icon_delete_item

    init {
        iconItemDelete.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                itemListener.onItemDeleted(adapterPosition)
            }
        }

        itemName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val name = s.toString()

                itemSaved.run {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        itemSaved.onItemNameUpdate(adapterPosition, name)
                    }
                }
            }
        })

        itemDescription.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val description = s.toString()

                itemSaved.run {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        itemSaved.onItemDescriptionUpdate(adapterPosition, description)
                    }
                }
            }
        })
    }

    fun bindItem(item: Item) {
        itemNumber.text = String.format(
            itemView.context
                .getString(R.string.item_number), adapterPosition + 1)

        itemName.setText(item.name)
        itemDescription.setText(item.description)

        iconItemDelete.visibility = if (adapterPosition == 0) View.GONE
        else View.VISIBLE
    }
}
