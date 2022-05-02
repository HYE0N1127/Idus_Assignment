package kr.hs.dgsw.idus_assignment.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import kr.hs.dgsw.idus_assignment.model.data.WeatherInfo

object WeatherDiffUtilCallBack : DiffUtil.ItemCallback<WeatherInfo>() {
    override fun areItemsTheSame(
        oldItem: WeatherInfo,
        newItem: WeatherInfo,
    ): Boolean {
        return oldItem.country == newItem.country
    }

    override fun areContentsTheSame(
        oldItem: WeatherInfo,
        newItem: WeatherInfo,
    ): Boolean {
        return oldItem == newItem
    }
}