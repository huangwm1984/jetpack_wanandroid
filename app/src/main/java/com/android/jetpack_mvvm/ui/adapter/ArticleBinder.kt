package com.android.jetpack_mvvm.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.android.base.BaseViewHolder
import com.android.jetpack_mvvm.BR
import com.android.jetpack_mvvm.R
import com.android.jetpack_mvvm.bean.Article
import com.android.jetpack_mvvm.databinding.ItemAriticleBinding
import com.drakeet.multitype.ItemViewDelegate


class ArticleBinder: ItemViewDelegate<Article, BaseViewHolder>() {
    override fun onBindViewHolder(holder: BaseViewHolder, item: Article) {
        holder.dataBinding.setVariable(BR.article, item)
        holder.dataBinding.executePendingBindings()
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup): BaseViewHolder {
        val binding: ItemAriticleBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_ariticle, parent, false)
        return BaseViewHolder(binding);
    }


}