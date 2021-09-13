package com.android.jetpack_mvvm.ui.adapter

import android.text.TextUtils
import com.android.base.toHtml
import com.android.jetpack_mvvm.R
import com.android.jetpack_mvvm.bean.Article
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseDelegateMultiAdapter
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate
import com.chad.library.adapter.base.viewholder.BaseViewHolder


class ArticleAdapter @JvmOverloads constructor(dataSource: MutableList<Article>? = mutableListOf()):
    BaseDelegateMultiAdapter<Article, BaseViewHolder>() {

    private val Article = 1//文章类型
    private val Project = 2//项目类型

    init {
        // 第一步，设置代理
        setMultiTypeDelegate(object : BaseMultiTypeDelegate<Article>() {
            override fun getItemType(data: List<Article>, position: Int): Int {
                return if (TextUtils.isEmpty(data[position].envelopePic)) Article else Project
            }
        })
        // 第二步，绑定 item 类型
        getMultiTypeDelegate()?.let {
            it.addItemType(Article, R.layout.item_ariticle)
            it.addItemType(Project, R.layout.item_project)
        }
    }

    override fun convert(holder: BaseViewHolder, item: Article) {
        when (holder.itemViewType) {
            Article -> {
                item.run {
                    holder.setText(R.id.item_home_author, if (author.isNotEmpty()) author else shareUser)
                    holder.setText(R.id.item_home_content, title.toHtml())
                    holder.setText(R.id.item_home_type, "$superChapterName·$chapterName".toHtml())
                    holder.setText(R.id.item_home_date, niceDate)
                    //展示标签
                    holder.setGone(R.id.item_home_new, !fresh)
                    holder.setGone(R.id.item_home_top, type != 1)
                    if (tags.isNotEmpty()) {
                        holder.setGone(R.id.item_home_type1, false)
                        holder.setText(R.id.item_home_type1, tags[0].name)
                    } else {
                        holder.setGone(R.id.item_home_type1, true)
                    }
                }
            }
            Project -> {
                item.run {
                    holder.setText(R.id.item_project_author, if (author.isNotEmpty()) author else shareUser)
                    holder.setText(R.id.item_project_title, title.toHtml())
                    holder.setText(R.id.item_project_content, desc.toHtml())
                    holder.setText(R.id.item_project_type, "$superChapterName·$chapterName".toHtml())
                    holder.setText(R.id.item_project_date, niceDate)
                    //展示标签
                    holder.setGone(R.id.item_project_new, !fresh)
                    holder.setGone(R.id.item_project_top, type != 1)
                    if (tags.isNotEmpty()) {
                        holder.setGone(R.id.item_project_type1, false)
                        holder.setText(R.id.item_project_type1, tags[0].name)
                    } else {
                        holder.setGone(R.id.item_project_type1, true)
                    }
                    Glide.with(context).load(envelopePic)
                        .transition(DrawableTransitionOptions.withCrossFade(500))
                        .into(holder.getView(R.id.item_project_imageview))
                }
            }
        }
    }


}


