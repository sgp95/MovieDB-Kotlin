package com.santiago.moviedb_kotlin.utils

import androidx.recyclerview.widget.RecyclerView

object ViewHelper {
    fun setLoadMoreListener(recyclerView: RecyclerView, onLoadMore: () -> Unit) {
        recyclerView.setOnScrollChangeListener { v, _, scrollY, _, oldScrollY ->
            v as RecyclerView
            v.getChildAt(v.childCount -1)?.let {
                if(scrollY >= (v.getChildAt(v.childCount -1).measuredHeight - v.measuredHeight) && scrollY > oldScrollY)
                    onLoadMore()
            }
        }
    }
}