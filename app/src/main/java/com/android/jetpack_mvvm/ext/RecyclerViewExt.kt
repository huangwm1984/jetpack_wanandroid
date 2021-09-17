package com.android.jetpack_mvvm.ext

import android.text.TextUtils
import android.util.TypedValue
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.base.dip2px
import com.android.jetpack_mvvm.MyApplication
import com.android.jetpack_mvvm.R
import com.android.jetpack_mvvm.bean.Article
import com.android.jetpack_mvvm.bean.CommonListData
import com.android.jetpack_mvvm.ui.adapter.ArticleBinder
import com.android.jetpack_mvvm.ui.adapter.ProjectBinder
import com.chad.library.adapter.base.BaseQuickAdapter
import com.drakeet.multitype.MultiTypeAdapter
import com.kingja.loadsir.core.LoadService
import com.yanzhenjie.recyclerview.SwipeRecyclerView
import com.yanzhenjie.recyclerview.widget.BorderItemDecoration
import kotlinx.android.synthetic.main.common_recyclerview.*

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

/**
 * 加载列表数据
 */
fun loadArticleListData(
    data: CommonListData<Article>,
    adapter: MultiTypeAdapter,
    loadService: LoadService<Any>,
    recyclerView: SwipeRecyclerView,
    swipeRefreshLayout: SwipeRefreshLayout
) {
    swipeRefreshLayout.isRefreshing = false
    recyclerView.loadMoreFinish(data.isEmpty, data.hasMore)
    if (data.isSuccess) {
        //成功
        when {
            //第一页并没有数据 显示空布局界面
            data.isFirstEmpty -> {
                loadService.showEmpty()
            }
            //是第一页
            data.isRefresh -> {
                adapter.items = data.datas
                loadService.showSuccess()
            }
            //不是第一页
            else -> {
                adapter.addData(data.datas)
                loadService.showSuccess()
            }
        }
    } else {
        //失败
        if (data.isRefresh) {
            //如果是第一页，则显示错误界面，并提示错误信息
            loadService.showError(data.errMessage)
        } else {
            recyclerView.loadMoreError(0, data.errMessage)
        }
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