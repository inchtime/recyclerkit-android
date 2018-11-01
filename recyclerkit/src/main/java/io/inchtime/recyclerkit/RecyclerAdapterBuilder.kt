package io.inchtime.recyclerkit

import android.app.Activity
import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import java.lang.Exception

class RecyclerAdapterBuilder(val context: Context, val spanCount: Int = 1) {

    private var recyclerView: RecyclerView? = null

    private val adapter: RecyclerAdapter = RecyclerAdapter(context, spanCount)

    fun recyclerView(recyclerView: RecyclerView): RecyclerAdapterBuilder {
        this.recyclerView = recyclerView
        this.recyclerView?.adapter = adapter
        return this
    }

    fun recyclerView(recyclerViewResId: Int): RecyclerAdapterBuilder {
        this.recyclerView = (context as Activity).findViewById(recyclerViewResId)
        this.recyclerView?.adapter = adapter
        return this
    }

    fun useLinearLayout(reverse: Boolean = false): RecyclerAdapterBuilder {
        if (recyclerView == null) throw Exception("please call recyclerView() function first!")
        recyclerView?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, reverse)
        return this
    }

    fun useGridLayout(): RecyclerAdapterBuilder {
        if (recyclerView == null) throw Exception("please call recyclerView() function first!")
        val layoutManager = GridLayoutManager(context, spanCount)
        layoutManager.spanSizeLookup = adapter.getSpanSizeLookup()
        recyclerView?.layoutManager = layoutManager
        return this
    }

    fun build(): RecyclerAdapter {
        return adapter
    }
}