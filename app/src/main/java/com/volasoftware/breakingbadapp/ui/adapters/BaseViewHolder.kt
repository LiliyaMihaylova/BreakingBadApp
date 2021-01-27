package com.volasoftware.breakingbadapp.ui.adapters

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<B : ViewDataBinding>(itemView: B) :
    RecyclerView.ViewHolder(itemView.root) {
    protected var binding: B = itemView
    protected var context: Context = itemView.root.context
}