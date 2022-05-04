package kr.hs.dgsw.idus_assignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.hs.dgsw.idus_assignment.adapter.callback.WeatherDiffUtilCallBack
import kr.hs.dgsw.idus_assignment.databinding.ItemHeaderBinding
import kr.hs.dgsw.idus_assignment.databinding.ItemWeatherBinding
import kr.hs.dgsw.idus_assignment.model.data.WeatherInfo
import java.lang.ClassCastException

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1

class WeatherAdapter :
    ListAdapter<DataItem, WeatherAdapter.WeatherHolder>(WeatherDiffUtilCallBack) {

    class WeatherHolder(private val binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItem) {
            if (binding is ItemWeatherBinding) {
                if (data is DataItem.WeatherItemWithHeader)
                    binding.weather = data.item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> ItemHeaderBinding.inflate(inflater, parent, false)
            ITEM_VIEW_TYPE_ITEM -> ItemWeatherBinding.inflate(inflater, parent, false)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
        return WeatherHolder(binding)
    }


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.WeatherItemWithHeader -> ITEM_VIEW_TYPE_ITEM
        }
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

sealed class DataItem {
    data class WeatherItemWithHeader(val item: WeatherInfo) : DataItem()
    object Header : DataItem()
}