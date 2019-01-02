package io.inchtime.recyclerkit.example.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import io.inchtime.recyclerkit.RecyclerAdapter
import io.inchtime.recyclerkit.RecyclerKit
import io.inchtime.recyclerkit.example.R
import io.inchtime.recyclerkit.example.common.BaseActivity
import io.inchtime.recyclerkit.example.model.AppStoreSecondaryItem
import kotlinx.android.synthetic.main.activity_list_example.*


class ListExampleActivity : BaseActivity() {

    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_example)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        adapter = RecyclerKit.adapter(this)
            .recyclerView(recyclerView)
            .withLinearLayout()
            .selectable(true, RecyclerAdapter.SelectionType.MULTI)
            .modelViewBind { index, viewModel, viewHolder ->
                when (viewModel.layout) {
                    R.layout.view_list_item -> {
                        bindListItem(viewModel, viewHolder, true)
                    }
                }
//                toast("modelViewBind: index: $index, viewModel: $viewModel")
            }
            .modelViewClick { index, viewModel ->
                toast("modelViewClick: index: $index, viewModel: $viewModel")
            }
            .modelViewLongClick { index, viewModel ->
//                toast("modelViewLongClick: index: $index, viewModel: $viewModel")
            }
            .build()

        val models = ArrayList<RecyclerAdapter.ViewModel>()

        val items = arrayListOf(
            AppStoreSecondaryItem(
                getString(R.string.app_store_title_3),
                getString(R.string.app_store_desc_3),
                R.drawable.app_store_clash_royale,
                0.0
            ),
            AppStoreSecondaryItem(
                getString(R.string.app_store_title_4),
                getString(R.string.app_store_desc_4),
                R.drawable.app_store_angry_brids,
                6.0
            )
        )

        for (index in 0..30) {

            val item = items[index % 2]

            models.add(
                RecyclerAdapter.ViewModel(
                    R.layout.view_list_item,
                    1,
                    RecyclerAdapter.ModelType.LEADING_TRAILING,
                    item
                )
            )
        }

        adapter.setModels(models)
    }

    private fun bindListItem(viewModel: RecyclerAdapter.ViewModel, viewHolder: RecyclerAdapter.ViewHolder, enableSelection: Boolean = false) {

        val item = viewModel.value as AppStoreSecondaryItem

        val titleTextView = viewHolder.findView<TextView>(R.id.titleTextView)
        val descTextView = viewHolder.findView<TextView>(R.id.descTextView)
        val imageView = viewHolder.findView<ImageView>(R.id.imageView)
        val priceButton = viewHolder.findView<Button>(R.id.priceButton)
        val checkImageView = viewHolder.findView<ImageView>(R.id.checkImageView)

        titleTextView.text = item.title
        descTextView.text = item.desc
        imageView.setImageResource(item.imageResId)
        priceButton.text =  if (item.price == 0.0) getString(R.string.get) else String.format("$%.2f", item.price)

        if (!enableSelection) {
            checkImageView.visibility = View.INVISIBLE
        } else {
            checkImageView.visibility = if (viewModel.selected) View.VISIBLE else View.INVISIBLE
        }

    }
}