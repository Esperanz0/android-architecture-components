package paging.android.example.com.pagingsample

import android.arch.paging.PagedListAdapter
import android.support.v7.recyclerview.extensions.DiffCallback
import android.view.ViewGroup

class CheeseAdapter : PagedListAdapter<Cheese, CheeseViewHolder>(DIFF_CALLBACK) {
    override fun onBindViewHolder(holder: CheeseViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheeseViewHolder =
            CheeseViewHolder(parent)

    companion object {
        private val DIFF_CALLBACK = object : DiffCallback<Cheese>() {
            override fun areItemsTheSame(oldItem: Cheese, newItem: Cheese): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Cheese, newItem: Cheese): Boolean =
                    oldItem == newItem
        }
    }
}