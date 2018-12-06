package io.inchtime.recyclerkit.example

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import io.inchtime.recyclerkit.RecyclerAdapter
import io.inchtime.recyclerkit.RecyclerKit
import io.inchtime.recyclerkit.example.activity.AppStoreJavaExampleActivity
import io.inchtime.recyclerkit.example.activity.ListExampleActivity
import io.inchtime.recyclerkit.example.common.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        val spanCount = 2

        val adapter = RecyclerKit.adapter(this, spanCount)
            .recyclerView(R.id.recyclerView)
            .withGridLayout()
            .modelViewBind { _, viewModel, viewHolder ->
                when (viewModel.layout) {
                    R.layout.view_examples_icon -> {
                        val pair = viewModel.value as Pair<*,*>
                        val icon = pair.first as Int
                        val title = pair.second as String
                        val iconImageView = viewHolder.findView<ImageView>(R.id.iconImageView)
                        val titleTextView = viewHolder.findView<TextView>(R.id.titleTextView)
                        iconImageView.setImageResource(icon)
                        titleTextView.text = title
                    }
                }
                return@modelViewBind
            }
            .emptyViewBind {
                return@emptyViewBind
            }
            .modelViewClick { _, viewModel ->
                val pair = viewModel.value as Pair<*,*>
                val icon = pair.first as Int
                when (icon) {
                    R.drawable.icon_list -> {
                        startActivity(Intent(this, ListExampleActivity::class.java))
                    }
                    R.drawable.icon_appstore -> {
                        startActivity(Intent(this, AppStoreJavaExampleActivity::class.java))
                    }
                }
                return@modelViewClick
            }
            .build()

        val models = arrayListOf(
            RecyclerAdapter.ViewModel(
                R.layout.view_examples_icon,
                1,
                RecyclerAdapter.ModelType.MIDDLE,
                Pair(R.drawable.icon_list, getString(R.string.list))
            ),
            RecyclerAdapter.ViewModel(
                R.layout.view_examples_icon,
                1,
                RecyclerAdapter.ModelType.MIDDLE,
                Pair(R.drawable.icon_appstore, getString(R.string.app_store))
            )
        )

        adapter.setModels(models)

        adapter.onModelViewBind = { _, viewModel, viewHolder ->
            when (viewModel.layout) {
                R.layout.view_examples_icon -> {
                    val pair = viewModel.value as Pair<*,*>
                    val icon = pair.first as Int
                    val title = pair.second as String
                    val iconImageView = viewHolder.findView<ImageView>(R.id.iconImageView)
                    val titleTextView = viewHolder.findView<TextView>(R.id.titleTextView)
                    iconImageView.setImageResource(icon)
                    titleTextView.text = title
                }
            }
        }

    }
}

