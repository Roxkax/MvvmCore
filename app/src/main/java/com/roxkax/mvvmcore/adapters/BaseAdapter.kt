package com.roxkax.mvvmcore.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Base class to reduce some of the boilerplate for creating a [RecyclerView.Adapter]
 */
class BaseAdapter<T, B : ViewDataBinding>(
    private val items: List<T>,
    private val itemsResourceId: Int,
    private val onBind: (View, B, T) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        object : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                itemsResourceId,
                parent,
                false
            )
        ) {}


    /**
     * Returns the number of elements of the source collection of the [RecyclerView.Adapter].
     * @return [Int] of with number of items
     */
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        DataBindingUtil.bind<B>(holder.itemView)?.let {
            onBind(holder.itemView, it, items[position])
        }
    }
}