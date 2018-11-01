package io.inchtime.recyclerkit

import android.content.Context
import android.support.annotation.NonNull
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RecyclerAdapter(context: Context, private val spanCount: Int = 1)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(), View.OnClickListener {

    companion object {
        const val VIEW_TYPE_EMPTY = Int.MAX_VALUE
    }

    data class Model(
        val layout: Int,
        val spanSize: Int,
        val type: ModelType,
        val value: Any)

    /**
     * identify the position of item in the items
     */
    enum class ModelType(val value: Int) {

        LEADING(0x01),
        MIDDLE(0x02),
        TRAILING(0x04),
        LEADING_TRAILING(0x05);

        companion object {
            fun valueOf(index: Int, @NonNull list: List<*>): ModelType {
                if (index < 0) throw IndexOutOfBoundsException()
                if (list.size <= 1) return LEADING_TRAILING
                if (index == 0) return LEADING
                if (index == list.size - 1) return TRAILING
                return MIDDLE
            }
        }
    }

    private val models = ArrayList<Model>()

    private lateinit var recyclerView: RecyclerView

    private var inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    var onModelSelectedListener: OnModelSelectedListener? = null

    var onModelBindListener: OnModelBindListener? = null

    var onEmptyViewBindListener: OnEmptyViewBindListener? = null

    var emptyViewVisibility: Boolean = true

    var emptyViewLayout: Int = R.layout.recyclerkit_view_empty

    /**
     * set the items of recycler adapter
     *
     * @param items items to display
     * @param append if false, clear the exist models, then add all items to models
     */
    fun setItems(items: ArrayList<Model>, append: Boolean = false) {
        if (!append) models.clear()
        models.addAll(items)
        notifyDataSetChanged()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun getItemViewType(position: Int): Int {
        return if (models.isEmpty()) {
            VIEW_TYPE_EMPTY
        } else models[position].layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder {

        return if (VIEW_TYPE_EMPTY == type) {
            val view = inflater.inflate(emptyViewLayout, parent, false)
            view.setOnClickListener(this)
            view.visibility = if (emptyViewVisibility) View.VISIBLE else View.INVISIBLE
            EmptyViewHolder(view)
        } else {
            // type is layout
            // see fun getItemViewType
            val view = inflater.inflate(type, parent, false)
            view.setOnClickListener(this)
            ViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return if (models.isNotEmpty()) models.size else 1
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        if (viewHolder is ViewHolder) {
            val model = models[position]
            onModelBindListener?.onModelBind(position, model, viewHolder)
        }

        if (viewHolder is EmptyViewHolder) {
            onEmptyViewBindListener?.onEmptyViewBind(viewHolder)
        }

    }

    override fun onClick(view: View) {
        val position = recyclerView.getChildAdapterPosition(view)

        if (!models.isEmpty() && position >= 0) {
            val model = models[position]
            onModelSelectedListener?.onModelSelected(position, model)
        }
    }

    fun getSpanSizeLookup(): GridLayoutManager.SpanSizeLookup {
        return object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                // empty spanCount must equal to GridLayoutManager's spanCount
                return if (models.isEmpty()) spanCount else models[position].spanSize
            }
        }
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val views: SparseArray<View> = SparseArray()

        fun <T: View> findView(key: Int): T {
            var v = views[key]
            if (v == null) {
                v = view.findViewById<T>(key)
                views.put(key, v)
            }
            @Suppress("UNCHECKED_CAST")
            return v as T
        }
    }

    class EmptyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }

    interface OnModelSelectedListener {
        fun onModelSelected(index: Int, model: Model)
    }

    interface OnModelBindListener {
        fun onModelBind(index: Int, model: Model, viewHolder: ViewHolder)
    }

    interface OnEmptyViewBindListener {
        fun onEmptyViewBind(viewHolder: EmptyViewHolder)
    }

}