package com.kardelencetin.tabletapplication.features.meals.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kardelencetin.tabletapplication.databinding.FragmentMealListBinding
import com.kardelencetin.tabletapplication.features.meals.domain.model.Meal
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealListFragment(
    private val onMealSelected: (Meal) -> Unit
) : Fragment() {
    private val viewModel: MealListViewModel by viewModels()
    private var _binding: FragmentMealListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMealListBinding.inflate(inflater, container, false)
        val adapter = MealListAdapter { meal -> onMealSelected(meal) }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.meals.observe(viewLifecycleOwner) { adapter.submitList(it) }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}