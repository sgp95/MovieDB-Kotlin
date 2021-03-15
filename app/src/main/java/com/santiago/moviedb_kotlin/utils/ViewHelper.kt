package com.santiago.moviedb_kotlin.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

object ViewHelper {
    fun setLoadMoreListener(recyclerView: RecyclerView, lastItemIndex: Int, onLoadMore: () -> Unit) {
        val layoutManager = recyclerView.layoutManager
        recyclerView.clearOnScrollListeners()
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                when (layoutManager) {
                    is LinearLayoutManager -> if(layoutManager.findLastVisibleItemPosition() == lastItemIndex) onLoadMore()
                    is GridLayoutManager -> if(layoutManager.findLastVisibleItemPosition() == lastItemIndex) onLoadMore()
                }
            }
        })

    }
}