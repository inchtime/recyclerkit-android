package io.inchtime.recyclerkit.example

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import io.inchtime.recyclerkit.RecyclerAdapter
import io.inchtime.recyclerkit.RecyclerKit
import io.inchtime.recyclerkit.example.common.BaseActivity
import io.inchtime.recyclerkit.example.model.HomeIcon

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
            .useGridLayout()
            .build()

        adapter.onModelBindListener = object : RecyclerAdapter.OnModelBindListener {
            override fun onModelBind(index: Int, model: RecyclerAdapter.Model, viewHolder: RecyclerAdapter.ViewHolder) {
                val icon = model.value as HomeIcon
                val iconImageView = viewHolder.findView<ImageView>(R.id.iconImageView)
                val titleTextView = viewHolder.findView<TextView>(R.id.titleTextView)
                iconImageView.setImageResource(icon.icon)
                titleTextView.text = icon.title
            }
        }

        val models = ArrayList<RecyclerAdapter.Model>()

        models.add(
            RecyclerAdapter.Model(
                R.layout.view_demo_icon,
                1,
                RecyclerAdapter.ModelType.MIDDLE,
                HomeIcon(R.drawable.icon_appstore, getString(R.string.app_store))
            )
        )

        models.add(
            RecyclerAdapter.Model(
                R.layout.view_demo_icon,
                1,
                RecyclerAdapter.ModelType.MIDDLE,
                HomeIcon(R.drawable.icon_netease_cloud_music, getString(R.string.netease_cloud_music))
            )
        )

        adapter.addItems(models)

    }
}

