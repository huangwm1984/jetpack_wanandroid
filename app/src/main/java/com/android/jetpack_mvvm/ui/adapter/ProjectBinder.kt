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
import com.android.jetpack_mvvm.databinding.ItemProjectBinding
import com.drakeet.multitype.ItemViewDelegate


class ProjectBinder: ItemViewDelegate<Article, BaseViewHolder>() {
    override fun onBindViewHolder(holder: BaseViewHolder, item: Article) {
        holder.dataBinding.setVariable(BR.article, item)
        holder.dataBinding.executePendingBindings()
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup): BaseViewHolder {
        val binding: ItemProjectBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_project, parent, false)
        return BaseViewHolder(binding);
    }


}