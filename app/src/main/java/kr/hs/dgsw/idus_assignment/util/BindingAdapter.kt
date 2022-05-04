package kr.hs.dgsw.idus_assignment.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("imageSrc")
    fun loadImage(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(Constants.IMAGE_URL + url + ".png")
            .into(imageView)
    }
}