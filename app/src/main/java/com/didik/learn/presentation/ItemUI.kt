package com.didik.learn.presentation

interface ItemUI {
    interface ItemSaved {
        fun onItemNameUpdate(position: Int, itemName: String)
        fun onItemDescriptionUpdate(position: Int, itemDescription: String)
    }

    interface ItemListener {
        fun onItemDeleted(position: Int)
    }
}
