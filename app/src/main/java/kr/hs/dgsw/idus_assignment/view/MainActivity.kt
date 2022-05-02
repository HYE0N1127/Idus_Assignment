package kr.hs.dgsw.idus_assignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import kr.hs.dgsw.idus_assignment.R
import kr.hs.dgsw.idus_assignment.adapter.WeatherAdapter
import kr.hs.dgsw.idus_assignment.databinding.ActivityMainBinding
import kr.hs.dgsw.idus_assignment.model.response.WeatherResponse
import kr.hs.dgsw.idus_assignment.viewmodel.MainViewModel
import java.lang.reflect.ParameterizedType
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        setContentView(binding.root)

        observerViewModel()
    }

    private fun observerViewModel() {
        val adapter = WeatherAdapter()
        binding.rvWeather.adapter = adapter

        with(viewModel) {
            itemList.observe(this@MainActivity, {
                adapter.submitList(it)
            })
           binding.swipeRefreshLayout.setOnRefreshListener {
                binding.swipeRefreshLayout.isRefreshing = false
                searchWeatherData(query)
            }
        }
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

}