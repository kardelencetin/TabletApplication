package com.kardelencetin.tabletapplication.features.meals.presentation.main

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kardelencetin.tabletapplication.databinding.ActivityMainBinding
import com.kardelencetin.tabletapplication.features.meals.domain.model.Meal
import com.kardelencetin.tabletapplication.features.meals.presentation.detail.MealDetailFragment
import com.kardelencetin.tabletapplication.features.meals.presentation.list.MealListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isTwoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        setWindowInsets()

        isTwoPane = binding.detailContainer != null

        if (isTwoPane) hideDivider()

        if (savedInstanceState == null) {
            showListFragment()
        }
    }

    private fun setWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun showListFragment() {
        val fragmentContainer = if (isTwoPane) binding.listContainer?.id ?: View.NO_ID else binding.root.id
        supportFragmentManager.beginTransaction()
            .replace(fragmentContainer, MealListFragment { item -> onItemSelected(item) })
            .commit()
    }

    private fun onItemSelected(item: Meal) {
        if (isTwoPane) {
            showDivider()
            showDetailContainer()
            supportFragmentManager.beginTransaction()
                .replace(binding.detailContainer!!.id, MealDetailFragment.newInstance(item.id))
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(binding.root.id, MealDetailFragment.newInstance(item.id))
                .addToBackStack(null)
                .commit()
        }
    }

    private fun showDetailContainer() {
        binding.detailContainer?.visibility = View.VISIBLE
    }

    private fun hideDivider() {
        binding.divider?.visibility = View.GONE
    }

    private fun showDivider() {
        binding.divider?.visibility = View.VISIBLE
    }
}
