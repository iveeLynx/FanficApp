package com.ivan.yaskovskyi.fanficapp.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


abstract class Pagination : RecyclerView.OnScrollListener() {

    private var layoutManager: LinearLayoutManager? = null

    fun PaginationScrollListener(layoutManager: LinearLayoutManager?) {
        this.layoutManager = layoutManager
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView!!, dx, dy)
    }

    protected abstract fun loadMoreItems()

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean
}