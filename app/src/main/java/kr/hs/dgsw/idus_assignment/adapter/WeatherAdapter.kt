package kr.hs.dgsw.idus_assignment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.idus_assignment.R
import kr.hs.dgsw.idus_assignment.adapter.WeatherAdapter.WeatherViewHolder
import kr.hs.dgsw.idus_assignment.adapter.callback.WeatherDiffUtilCallBack
import kr.hs.dgsw.idus_assignment.databinding.ItemWeatherBinding
import kr.hs.dgsw.idus_assignment.model.data.WeatherInfo
import java.text.DecimalFormat

class WeatherAdapter :
    ListAdapter<WeatherInfo, WeatherViewHolder>(WeatherDiffUtilCallBack) {

    class WeatherViewHolder(private val binding: ItemWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(data: WeatherInfo) {
            val todayTemp = String.format("%.0f", data.today.theTemp)
            val tomorrowTemp = String.format("%.0f", data.nextDay.theTemp)

            binding.tvHumidityToday.text = data.today.humidity.toString() + "%"
            binding.tvHumidityTomorrow.text = data.nextDay.humidity.toString() + "%"

            binding.tvWeatherStateToday.text = data.today.weatherStateName
            binding.tvWeatherStateTomorrow.text = data.nextDay.weatherStateName

            binding.tvTemperatureToday.text = "$tomorrowTemp°C"
            binding.tvTemperatureTomorrow.text = "$todayTemp°C"

            binding.tvLocal.text = data.country

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemWeatherBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_weather, parent, false)

        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}