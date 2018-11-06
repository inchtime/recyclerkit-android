package io.inchtime.recyclerkit.example.activity

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import io.inchtime.recyclerkit.RecyclerAdapter
import io.inchtime.recyclerkit.RecyclerKit
import io.inchtime.recyclerkit.example.R
import io.inchtime.recyclerkit.example.common.BaseActivity
import io.inchtime.recyclerkit.example.model.AppStoreSecondaryItem
import kotlinx.android.synthetic.main.activity_appstore.*


class ListExampleActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appstore)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        val adapter = RecyclerKit.adapter(this)
            .recyclerView(recyclerView)
            .withLinearLayout()
            .modelViewBind { index, viewModel, viewHolder ->
                when (viewModel.layout) {
                    R.layout.view_list_item -> {
                        bindListItem(index, viewModel, viewHolder)
                    }
                }
            }
            .build()

        val models = ArrayList<RecyclerAdapter.ViewModel>()

        for (index in 0..30) {
            models.add(
                RecyclerAdapter.ViewModel(
                    R.layout.view_list_item,
                    1,
                    RecyclerAdapter.ModelType.LEADING_TRAILING,
                    0
                )
            )
        }

        adapter.setItems(models)
    }

    private fun bindListItem(index: Int, viewModel: RecyclerAdapter.ViewModel, viewHolder: RecyclerAdapter.ViewHolder) {

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

        val item = items[index % 2]

        val titleTextView = viewHolder.findView<TextView>(R.id.titleTextView)
        val descTextView = viewHolder.findView<TextView>(R.id.descTextView)
        val imageView = viewHolder.findView<ImageView>(R.id.imageView)
        val priceButton = viewHolder.findView<Button>(R.id.priceButton)

        titleTextView.text = item.title
        descTextView.text = item.desc
        imageView.setImageResource(item.imageResId)
        priceButton.text =  if (item.price == 0.0) getString(R.string.get) else String.format("$%.2f", item.price)

    }
}