package io.inchtime.recyclerkit.example.activity

import android.graphics.Outline
import android.os.Build
import android.os.Bundle
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import io.inchtime.recyclerkit.RecyclerAdapter
import io.inchtime.recyclerkit.RecyclerKit
import io.inchtime.recyclerkit.example.R
import io.inchtime.recyclerkit.example.common.BaseActivity
import io.inchtime.recyclerkit.example.model.AppStorePrimaryItem
import io.inchtime.recyclerkit.example.model.AppStoreSecondaryItem
import kotlinx.android.synthetic.main.activity_appstore_example.*
import android.graphics.BitmapFactory
import android.graphics.Bitmap



class AppStoreExampleActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appstore_example)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        val adapter = RecyclerKit.adapter(this)
            .recyclerView(recyclerView)
            .withLinearLayout()
            .modelViewBind { _, viewModel, viewHolder ->
                when (viewModel.layout) {
                    R.layout.view_app_store_primary -> {
                        bindAppStorePrimary(viewModel, viewHolder)
                    }
                    R.layout.view_app_store_secondary -> {
                        bindAppStoreSecondary(viewModel, viewHolder)
                    }
                }
            }
            .build()

        val models = ArrayList<RecyclerAdapter.ViewModel>()

        // Add Horizontal Recycler View
        models.add(
            RecyclerAdapter.ViewModel(
                R.layout.view_app_store_primary,
                1,
                RecyclerAdapter.ModelType.LEADING_TRAILING,
                0
            )
        )

        // Add Category Title
        models.add(
            RecyclerAdapter.ViewModel(
                R.layout.view_app_store_category_title,
                1,
                RecyclerAdapter.ModelType.LEADING_TRAILING,
                0
            )
        )

        // Add Horizontal Recycler View
        models.add(
            RecyclerAdapter.ViewModel(
                R.layout.view_app_store_secondary,
                1,
                RecyclerAdapter.ModelType.LEADING_TRAILING,
                0
            )
        )

        // Add Horizontal Recycler View
        models.add(
            RecyclerAdapter.ViewModel(
                R.layout.view_app_store_primary,
                1,
                RecyclerAdapter.ModelType.LEADING_TRAILING,
                0
            )
        )

        // Add Category Title
        models.add(
            RecyclerAdapter.ViewModel(
                R.layout.view_app_store_category_title,
                1,
                RecyclerAdapter.ModelType.LEADING_TRAILING,
                0
            )
        )

        // Add Horizontal Recycler View
        models.add(
            RecyclerAdapter.ViewModel(
                R.layout.view_app_store_secondary,
                1,
                RecyclerAdapter.ModelType.LEADING_TRAILING,
                0
            )
        )

        adapter.addItems(models)
    }

    private fun bindAppStorePrimary(viewModel: RecyclerAdapter.ViewModel, viewHolder: RecyclerAdapter.ViewHolder) {

        val pRecyclerView = viewHolder.findView<RecyclerView>(R.id.recyclerView)

        val adapter = RecyclerKit.adapter(this)
            .recyclerView(pRecyclerView)
            .withLinearLayout(LinearLayoutManager.HORIZONTAL)
            .modelViewBind { _, pViewModel, pViewHolder ->
                when (pViewModel.layout) {
                    R.layout.view_app_store_primary_item -> {
                        bindAppStorePrimaryItem(pViewModel, pViewHolder)
                    }
                }
            }
            .build()

        val items = arrayListOf(
            AppStorePrimaryItem(
                getString(R.string.app_store_category_1),
                getString(R.string.app_store_title_1),
                getString(R.string.app_store_desc_1),
                R.drawable.app_store_brothers
            ),
            AppStorePrimaryItem(
                getString(R.string.app_store_category_2),
                getString(R.string.app_store_title_2),
                getString(R.string.app_store_desc_2),
                R.drawable.app_store_marvel_future_fight
            )
        )

        val models = ArrayList<RecyclerAdapter.ViewModel>()

        for (item in items) {
            models.add(
                RecyclerAdapter.ViewModel(
                    R.layout.view_app_store_primary_item,
                    1,
                    RecyclerAdapter.ModelType.LEADING,
                    item
                )
            )
        }
        adapter.addItems(models)

    }

    private fun bindAppStorePrimaryItem(viewModel: RecyclerAdapter.ViewModel, viewHolder: RecyclerAdapter.ViewHolder) {

        val item = viewModel.value as AppStorePrimaryItem

        val categoryTextView = viewHolder.findView<TextView>(R.id.categoryTextView)
        val titleTextView = viewHolder.findView<TextView>(R.id.titleTextView)
        val descTextView = viewHolder.findView<TextView>(R.id.descTextView)
        val imageView = viewHolder.findView<ImageView>(R.id.imageView)


        categoryTextView.text = item.category
        titleTextView.text = item.title
        descTextView.text = item.desc

        val bitmap = BitmapFactory.decodeResource(resources, item.imageResId)
        val drawable = RoundedBitmapDrawableFactory.create(resources, bitmap)
        drawable.cornerRadius = 30f
        imageView.setImageDrawable(drawable)
    }

    private fun bindAppStoreSecondary(viewModel: RecyclerAdapter.ViewModel, viewHolder: RecyclerAdapter.ViewHolder) {

        val pRecyclerView = viewHolder.findView<RecyclerView>(R.id.recyclerView)

        val adapter = RecyclerKit.adapter(this, 2)
            .recyclerView(pRecyclerView)
            .withGridLayout(LinearLayoutManager.HORIZONTAL)
            .modelViewBind { _, pViewModel, pViewHolder ->
                when (pViewModel.layout) {
                    R.layout.view_app_store_secondary_item -> {
                        bindAppStoreSecondaryItem(pViewModel, pViewHolder)
                    }
                }
            }
            .build()

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
            ),
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

        val models = ArrayList<RecyclerAdapter.ViewModel>()

        for (item in items) {
            models.add(
                RecyclerAdapter.ViewModel(
                    R.layout.view_app_store_secondary_item,
                    1,
                    RecyclerAdapter.ModelType.LEADING,
                    item
                )
            )
        }
        adapter.addItems(models)

    }

    private fun bindAppStoreSecondaryItem(
        viewModel: RecyclerAdapter.ViewModel,
        viewHolder: RecyclerAdapter.ViewHolder
    ) {

        val item = viewModel.value as AppStoreSecondaryItem

        val titleTextView = viewHolder.findView<TextView>(R.id.titleTextView)
        val descTextView = viewHolder.findView<TextView>(R.id.descTextView)
        val imageView = viewHolder.findView<ImageView>(R.id.imageView)
        val priceButton = viewHolder.findView<Button>(R.id.priceButton)

        titleTextView.text = item.title
        descTextView.text = item.desc
        imageView.setImageResource(item.imageResId)
        priceButton.text = if (item.price == 0.0) getString(R.string.get) else String.format("$%.2f", item.price)
    }
}
