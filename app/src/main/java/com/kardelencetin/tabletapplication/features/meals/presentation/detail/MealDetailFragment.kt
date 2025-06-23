package com.kardelencetin.tabletapplication.features.meals.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.kardelencetin.tabletapplication.databinding.FragmentMealDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealDetailFragment : Fragment() {
    private val viewModel: MealDetailViewModel by viewModels()
    private var _binding: FragmentMealDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealDetailBinding.inflate(inflater, container, false)
        val mealId = requireArguments().getString("id") ?: ""
        viewModel.fetchMeal(mealId)
        viewModel.meal.observe(viewLifecycleOwner) { meal ->
            binding.titleMeal.text = meal?.title
            binding.toolbar.title = meal?.title
            binding.descriptionMeal.text = meal?.description
            Glide.with(requireContext())
                .load(meal?.image)
                .centerCrop()
                .placeholder(null)
                .into(binding.mealImage)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(id: String) = MealDetailFragment().apply {
            arguments = Bundle().apply {
                putString("id", id)
            }
        }
    }
}
