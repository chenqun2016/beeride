package com.chenchen.base.utils

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.chenchen.base.R

/**
 * 创建时间：2021/7/7
 * 编写人： 陈陈陈
 * 功能描述：加载更多工具
 * 注意：adapter需要 extends BaseQuickAdapter/BaseMultiItemQuickAdapter  implements LoadMoreModule
 */
open class LoadmoreUtils<T> {
    companion object {
        const val PAGE_SIZE = 10
    }

    constructor(adapter: BaseQuickAdapter<T, BaseViewHolder>) {
        initLoadmore(adapter, null)
    }

    constructor(
        adapter: BaseQuickAdapter<T, BaseViewHolder>,
        swiperefreshlayout: SwipeRefreshLayout?
    ) {
        initLoadmore(adapter, swiperefreshlayout)
    }

    var emptyView: View? = null
    var swiperefreshlayout: SwipeRefreshLayout? = null
    var pageInfo = PageInfo()
    lateinit var mAdapter: BaseQuickAdapter<T, BaseViewHolder>

    class PageInfo {
        var page = 1
        fun nextPage() {
            page++
        }

        fun reset() {
            page = 1
        }

        val isFirstPage: Boolean
            get() = page == 1
    }

    private fun initLoadmore(
        adapter: BaseQuickAdapter<T, BaseViewHolder>,
        swipe: SwipeRefreshLayout?
    ) {
        mAdapter = adapter
        adapter.loadMoreModule.setOnLoadMoreListener { loadMore() }
        adapter.loadMoreModule.isAutoLoadMore = true
        emptyView = LayoutInflater.from(adapter.recyclerView.context)
            .inflate(R.layout.crecyclerview_empty, adapter.recyclerView, false)
        swiperefreshlayout = swipe
        if (null != swipe) {
            swiperefreshlayout?.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimaryDark
            )
            swiperefreshlayout?.setOnRefreshListener { onRefreshData() }
        }
    }

    fun setEmptyViewText(text: String?) {
        val tvEmptyText = emptyView?.findViewById<TextView>(R.id.tv_empty)
        tvEmptyText?.text = text
    }

    fun startSwipeRefreshView() {
        if (null != swiperefreshlayout) {
            if (swiperefreshlayout?.isRefreshing == true) {
                swiperefreshlayout?.isRefreshing = false
            }
            swiperefreshlayout?.isRefreshing = true
        }
    }

    fun refresh() {
        mAdapter.loadMoreModule.isEnableLoadMore = false
        // 下拉刷新，需要重置页数
        pageInfo.reset()
        startSwipeRefreshView()
        request()
    }

    private fun onRefreshData() {
        mAdapter.loadMoreModule.isEnableLoadMore = false
        // 下拉刷新，需要重置页数
        pageInfo.reset()
        request()
    }

    fun reSetPageInfo() {
        pageInfo.reset()
    }

    /**
     * 加载更多
     */
    private fun loadMore() {
        request()
    }

    /**
     * 请求数据
     */
    private fun request() {
        getDatas(pageInfo.page)
    }

    protected open fun getDatas(page: Int) {}
    fun onFail(e: String?) {
        mAdapter.loadMoreModule.isEnableLoadMore = true
        mAdapter.loadMoreModule.loadMoreFail()
        closeSwipeRefreshLayout()
    }

    fun onSuccess(data: List<T>?) {
        mAdapter.loadMoreModule.isEnableLoadMore = true
        if (pageInfo.isFirstPage) {
            //如果是加载的第一页数据，用 setData()
            mAdapter.setList(data)
            if (!mAdapter.hasEmptyView() && null != emptyView) {
                mAdapter.setEmptyView(emptyView!!)
            }
        } else {
            //不是第一页，则用add
            if (null != data) {
                mAdapter.addData(data)
            }
        }
        if (null != data && data.size < PAGE_SIZE) {
            //如果不够一页,显示没有更多数据布局
            mAdapter.loadMoreModule.loadMoreEnd()
        } else {
            mAdapter.loadMoreModule.loadMoreComplete()
        }
        // page加一
        pageInfo.nextPage()
        closeSwipeRefreshLayout()
    }

    /**
     * 关闭SwipeRefreshLayout
     */
    private fun closeSwipeRefreshLayout() {
        swiperefreshlayout?.isRefreshing = false
    }

}