package com.android.jetpack_mvvm.ext

import android.text.TextUtils
import android.util.TypedValue
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.base.dip2px
import com.android.jetpack_mvvm.MyApplication
import com.android.jetpack_mvvm.R
import com.android.jetpack_mvvm.bean.Article
import com.android.jetpack_mvvm.ui.adapter.ArticleBinder
import com.android.jetpack_mvvm.ui.adapter.ProjectBinder
import com.drakeet.multitype.MultiTypeAdapter
import com.yanzhenjie.recyclerview.SwipeRecyclerView
import com.yanzhenjie.recyclerview.widget.BorderItemDecoration

fun SwipeRecyclerView.init(
    layoutManger: RecyclerView.LayoutManager,
    bindAdapter: RecyclerView.Adapter<*>,
): SwipeRecyclerView {
    layoutManager = layoutManger
    adapter = bindAdapter
    return this
}

fun SwipeRecyclerView.initFooter(loadMoreListener: SwipeRecyclerView.LoadMoreListener) {
    this.run {
        addItemDecoration(
            BorderItemDecoration(ContextCompat.getColor(MyApplication.instance, android.R.color.transparent),
                dip2px(context, 8F),
                dip2px(context, 8F))
        )

        //使用默认的加载更多的View
        useDefaultLoadMore()
        setLoadMoreListener(loadMoreListener)
    }
}

fun MultiTypeAdapter.initArticleAdapter() {
    this.register(Article::class).to(
        ArticleBinder(),
        ProjectBinder()
    ).withKotlinClassLinker { _, data ->
        when (TextUtils.isEmpty(data.envelopePic)) {
            true -> ArticleBinder::class
            else -> ProjectBinder::class
        }
    }
}

fun MultiTypeAdapter.addData(@NonNull newData: Collection<Any>) {
    var data: MutableList<Any> = this.items as MutableList<Any>
    data.addAll(newData)
    notifyItemRangeInserted(data.size - newData.size, newData.size)
    this.compatibilityDataSizeChanged(newData.size)
}

fun MultiTypeAdapter.compatibilityDataSizeChanged(size: Int) {
    if (this.items.size == size) {
        notifyDataSetChanged()
    }
}