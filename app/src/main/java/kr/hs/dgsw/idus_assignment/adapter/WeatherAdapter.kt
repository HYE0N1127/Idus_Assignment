package kr.hs.dgsw.idus_assignment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.hs.dgsw.idus_assignment.R
import kr.hs.dgsw.idus_assignment.adapter.WeatherAdapter.WeatherViewHolder
import kr.hs.dgsw.idus_assignment.adapter.callback.WeatherDiffUtilCallBack
import kr.hs.dgsw.idus_assignment.databinding.ItemWeatherBinding
import kr.hs.dgsw.idus_assignment.model.data.WeatherInfo
import kr.hs.dgsw.idus_assignment.util.Constants

class WeatherAdapter :
    ListAdapter<WeatherInfo, WeatherViewHolder>(WeatherDiffUtilCallBack) {

    class WeatherViewHolder(private val binding: ItemWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: WeatherInfo) {
            binding.weather = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemWeatherBinding = ItemWeatherBinding.inflate(layoutInflater, parent, false)

        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}