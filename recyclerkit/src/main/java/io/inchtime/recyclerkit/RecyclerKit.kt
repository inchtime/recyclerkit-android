package io.inchtime.recyclerkit

import android.content.Context

object RecyclerKit {

    fun adapter(context: Context, spanCount: Int = 1): RecyclerAdapterBuilder {
        return RecyclerAdapterBuilder(context, spanCount)
    }

}