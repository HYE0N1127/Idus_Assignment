package kr.hs.dgsw.idus_assignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.idus_assignment.R
import kr.hs.dgsw.idus_assignment.adapter.WeatherAdapter
import kr.hs.dgsw.idus_assignment.databinding.ActivityMainBinding
import kr.hs.dgsw.idus_assignment.viewmodel.MainViewModel
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val weatherAdapter = WeatherAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        setContentView(binding.root)

        binding.rvWeather.adapter = weatherAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            viewModel.searchWeatherData()
        }
        observerViewModel()
    }

    private fun observerViewModel() {
        viewModel.itemList.observe(this) {
            weatherAdapter.submitList(it)
        }
    }

    private fun performDataBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }

}