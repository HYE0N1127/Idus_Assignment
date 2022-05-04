package kr.hs.dgsw.idus_assignment.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import kr.hs.dgsw.idus_assignment.adapter.DataItem
import kr.hs.dgsw.idus_assignment.adapter.DataItem.*
import kr.hs.dgsw.idus_assignment.model.data.WeatherInfo
import kr.hs.dgsw.idus_assignment.model.response.WeatherItem

object WeatherDiffUtilCallBack : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem is WeatherItemWithHeader && newItem is WeatherItemWithHeader && oldItem.item.country == newItem.item.country

    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean =
        oldItem == newItem
}