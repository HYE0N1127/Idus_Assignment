package kr.hs.dgsw.idus_assignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
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
    ListAdapter<DataItem, RecyclerView.ViewHolder>(WeatherDiffUtilCallBack) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    class WeatherViewHolder(private val binding: ItemWeatherBinding) :
        ViewHolder(binding.root) {

        fun bind(data: DataItem.WeatherItemWithHeader) {
            binding.weather = data.item
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemWeatherBinding.inflate(layoutInflater, parent, false)
                return WeatherViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> HeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> WeatherViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.WeatherItemWithHeader -> ITEM_VIEW_TYPE_ITEM
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is WeatherViewHolder -> {
                val weatherItem = getItem(position) as DataItem.WeatherItemWithHeader
                holder.bind(weatherItem)
            }
        }
    }

    fun addHeaderAndSubmitList(list: List<WeatherInfo>?) {
        adapterScope.launch {
            val itemList = ArrayList<DataItem>()

            itemList.add(DataItem.Header)
            list?.let { itemList.addAll(it.map { DataItem.WeatherItemWithHeader(it) }) }

            withContext(Dispatchers.Main) {
                submitList(itemList)
            }
        }
    }
}

sealed class DataItem {
    data class WeatherItemWithHeader(val item: WeatherInfo) : DataItem()
    object Header : DataItem()
}

class HeaderViewHolder(binding: ItemHeaderBinding) : ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): HeaderViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemHeaderBinding.inflate(layoutInflater, parent, false)
            return HeaderViewHolder(binding)
        }
    }
}