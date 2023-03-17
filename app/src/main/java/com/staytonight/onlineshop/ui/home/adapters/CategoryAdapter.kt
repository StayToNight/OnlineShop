package com.staytonight.onlineshop.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.staytonight.domain.model.Category
import com.staytonight.onlineshop.databinding.ItemCategoryBinding

class CategoryAdapter(
    private val items: MutableList<Category>
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class CategoryViewHolder(
        private val item: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(item.root) {

        fun bind(category: Category) {
            item.apply {
                tvCategory.text = category.title
                category.image.toIntOrNull()?.let { ivCategory.setImageResource(it) }
            }
        }

    }
}