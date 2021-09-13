package me.hgj.jetpackmvvm.demo.app.weight.banner

import android.view.View
import com.android.jetpack_mvvm.R
import com.android.jetpack_mvvm.bean.Banner
import com.zhpan.bannerview.BaseBannerAdapter

class HomeBannerAdapter : BaseBannerAdapter<Banner, HomeBannerViewHolder>() {
    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_banner_home
    }

    override fun createViewHolder(itemView: View, viewType: Int): HomeBannerViewHolder {
        return HomeBannerViewHolder(itemView);
    }

    override fun onBind(
        holder: HomeBannerViewHolder?,
        data: Banner?,
        position: Int,
        pageSize: Int,
    ) {
        holder?.bindData(data, position, pageSize);
    }
}
