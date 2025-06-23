package com.kardelencetin.tabletapplication.features.meals.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kardelencetin.tabletapplication.databinding.ListItemBinding
import com.kardelencetin.tabletapplication.features.meals.domain.model.Meal

class MealListAdapter(
    private val onClick: (Meal) -> Unit
) : ListAdapter<Meal, MealListAdapter.MealViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Meal>() {
            override fun areItemsTheSame(old: Meal, new: Meal) = old.id == new.id
            override fun areContentsTheSame(old: Meal, new: Meal) = old == new
        }
    }

    inner class MealViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(meal: Meal) {
            binding.titleMeal.text = meal.title
            Glide.with(binding.mealImage.context)
                .load(meal.image)
                .centerCrop()
                .placeholder(null)
                .into(binding.mealImage)
            binding.root.setOnClickListener { onClick(meal) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) = holder.bind(getItem(position))
}
