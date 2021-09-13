package me.hgj.jetpackmvvm.demo.app.weight.banner

import android.view.View
import android.widget.ImageView
import com.android.jetpack_mvvm.MyApplication
import com.android.jetpack_mvvm.R
import com.android.jetpack_mvvm.bean.Banner
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.zhpan.bannerview.BaseViewHolder

class HomeBannerViewHolder(view: View) : BaseViewHolder<Banner>(view) {
    override fun bindData(data: Banner?, position: Int, pageSize: Int) {
        val img = itemView.findViewById<ImageView>(R.id.banner_img)
        data?.run {
            Glide.with(MyApplication.instance)
                .load(imagePath)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(img)
        }
    }

}
