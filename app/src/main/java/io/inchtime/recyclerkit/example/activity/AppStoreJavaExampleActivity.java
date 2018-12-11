package io.inchtime.recyclerkit.example.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import io.inchtime.recyclerkit.RecyclerAdapter;
import io.inchtime.recyclerkit.RecyclerKit;
import io.inchtime.recyclerkit.example.R;
import io.inchtime.recyclerkit.example.model.AppStorePrimaryItem;
import io.inchtime.recyclerkit.example.model.AppStoreSecondaryItem;
import kotlin.Unit;

import java.util.ArrayList;
import java.util.List;

public class AppStoreJavaExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_example);
        setupRecyclerView();
    }

    private void setupRecyclerView() {

        RecyclerAdapter adapter = RecyclerKit.INSTANCE.adapter(this, 1)
                .recyclerView(R.id.recyclerView)
                .withLinearLayout(LinearLayoutManager.VERTICAL, false)
                .modelViewBind((index, viewModel, viewHolder) -> {
                    switch (viewModel.getLayout()) {
                        case R.layout.view_app_store_primary:
                            bindAppStorePrimary(viewModel, viewHolder);
                            break;
                        case R.layout.view_app_store_secondary:
                            bindAppStoreSecondary(viewModel, viewHolder);
                            break;
                    }
                    return Unit.INSTANCE;
                })
                .emptyViewBind((emptyViewHolder -> {
                    return Unit.INSTANCE;
                }))
                .build();

        adapter.setEmptyView(R.layout.recyclerkit_view_empty);

        List<RecyclerAdapter.ViewModel> models = new ArrayList<>();

        // Add Horizontal Recycler View
        models.add(
                new RecyclerAdapter.ViewModel(
                        R.layout.view_app_store_primary,
                        1,
                        RecyclerAdapter.ModelType.LEADING_TRAILING,
                        0,
                        false)
        );

        // Add Category Title
        models.add(
                new RecyclerAdapter.ViewModel(
                        R.layout.view_app_store_category_title,
                        1,
                        RecyclerAdapter.ModelType.LEADING_TRAILING,
                        0,
                        false
                )
        );

        // Add Horizontal Recycler View
        models.add(
                new RecyclerAdapter.ViewModel(
                        R.layout.view_app_store_secondary,
                        1,
                        RecyclerAdapter.ModelType.LEADING_TRAILING,
                        0,
                        false
                )
        );

        // Add Horizontal Recycler View
        models.add(
                new RecyclerAdapter.ViewModel(
                        R.layout.view_app_store_primary,
                        1,
                        RecyclerAdapter.ModelType.LEADING_TRAILING,
                        0,
                        false
                )
        );

        // Add Category Title
        models.add(
                new RecyclerAdapter.ViewModel(
                        R.layout.view_app_store_category_title,
                        1,
                        RecyclerAdapter.ModelType.LEADING_TRAILING,
                        0,
                        false
                )
        );

        // Add Horizontal Recycler View
        models.add(
                new RecyclerAdapter.ViewModel(
                        R.layout.view_app_store_secondary,
                        1,
                        RecyclerAdapter.ModelType.LEADING_TRAILING,
                        0,
                        false
                )
        );

        adapter.addModels(models);
    }

    private void bindAppStorePrimary(RecyclerAdapter.ViewModel viewModel, RecyclerAdapter.ViewHolder viewHolder) {

        RecyclerView pRecyclerView = viewHolder.findView(R.id.recyclerView);

        RecyclerAdapter adapter = RecyclerKit.INSTANCE.adapter(this, 1)
                .recyclerView(pRecyclerView)
                .withLinearLayout(LinearLayoutManager.HORIZONTAL, false)
                .modelViewBind((pIndex, pViewModel, pViewHolder) -> {
                    switch (pViewModel.getLayout()) {
                        case R.layout.view_app_store_primary_item:
                            bindAppStorePrimaryItem(pViewModel, pViewHolder);
                            break;
                    }

                    return Unit.INSTANCE;
                })
                .build();

        List<AppStorePrimaryItem> items = new ArrayList<>();

        items.add(
                new AppStorePrimaryItem(
                        getString(R.string.app_store_category_1),
                        getString(R.string.app_store_title_1),
                        getString(R.string.app_store_desc_1),
                        R.drawable.app_store_brothers
                )
        );

        items.add(
                new AppStorePrimaryItem(
                        getString(R.string.app_store_category_2),
                        getString(R.string.app_store_title_2),
                        getString(R.string.app_store_desc_2),
                        R.drawable.app_store_marvel_future_fight
                )
        );

        List<RecyclerAdapter.ViewModel> models = new ArrayList<>();

        for (AppStorePrimaryItem item : items) {
            models.add(
                    new RecyclerAdapter.ViewModel(
                            R.layout.view_app_store_primary_item,
                            1,
                            RecyclerAdapter.ModelType.LEADING,
                            item,
                            false
                    )
            );
        }
        adapter.addModels(models);

    }

    private void bindAppStorePrimaryItem(RecyclerAdapter.ViewModel viewModel, RecyclerAdapter.ViewHolder viewHolder) {

        AppStorePrimaryItem item = (AppStorePrimaryItem) viewModel.getValue();

        TextView categoryTextView = viewHolder.findView(R.id.categoryTextView);
        TextView titleTextView = viewHolder.findView(R.id.titleTextView);
        TextView descTextView = viewHolder.findView(R.id.descTextView);
        ImageView imageView = viewHolder.findView(R.id.imageView);

        categoryTextView.setText(item.getCategory());
        titleTextView.setText(item.getTitle());
        descTextView.setText(item.getDesc());

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), item.getImageResId());
        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        drawable.setCornerRadius(30f);
        imageView.setImageDrawable(drawable);
    }

    private void bindAppStoreSecondary(RecyclerAdapter.ViewModel viewModel, RecyclerAdapter.ViewHolder viewHolder) {

        RecyclerView pRecyclerView = viewHolder.findView(R.id.recyclerView);

        RecyclerAdapter adapter = RecyclerKit.INSTANCE.adapter(this, 2)
                .recyclerView(pRecyclerView)
                .withGridLayout(GridLayoutManager.HORIZONTAL, false)
                .modelViewBind((pIndex, pViewModel, pViewHolder) -> {
                    switch (pViewModel.getLayout()) {
                        case R.layout.view_app_store_secondary_item:
                            bindAppStoreSecondaryItem(pViewModel, pViewHolder);
                            break;
                    }
                    return Unit.INSTANCE;
                })
                .build();

        List<AppStoreSecondaryItem> items = new ArrayList<>();


        items.add(
                new AppStoreSecondaryItem(
                        getString(R.string.app_store_title_3),
                        getString(R.string.app_store_desc_3),
                        R.drawable.app_store_clash_royale,
                        0.0
                ));

        items.add(
                new AppStoreSecondaryItem(
                        getString(R.string.app_store_title_4),
                        getString(R.string.app_store_desc_4),
                        R.drawable.app_store_angry_brids,
                        6.0
                ));

        items.add(
                new AppStoreSecondaryItem(
                        getString(R.string.app_store_title_3),
                        getString(R.string.app_store_desc_3),
                        R.drawable.app_store_clash_royale,
                        0.0
                ));

        items.add(
                new AppStoreSecondaryItem(
                        getString(R.string.app_store_title_4),
                        getString(R.string.app_store_desc_4),
                        R.drawable.app_store_angry_brids,
                        6.0
                ));

        List<RecyclerAdapter.ViewModel> models = new ArrayList<>();

        for (AppStoreSecondaryItem item : items) {
            models.add(
                    new RecyclerAdapter.ViewModel(
                            R.layout.view_app_store_secondary_item,
                            1,
                            RecyclerAdapter.ModelType.LEADING,
                            item,
                            false
                    )
            );
        }

        adapter.addModels(models);

    }

    private void bindAppStoreSecondaryItem(RecyclerAdapter.ViewModel viewModel, RecyclerAdapter.ViewHolder viewHolder) {

        AppStoreSecondaryItem item = (AppStoreSecondaryItem) viewModel.getValue();

        TextView titleTextView = viewHolder.findView(R.id.titleTextView);
        TextView descTextView = viewHolder.findView(R.id.descTextView);
        ImageView imageView = viewHolder.findView(R.id.imageView);
        Button priceButton = viewHolder.findView(R.id.priceButton);

        titleTextView.setText(item.getTitle());
        descTextView.setText(item.getDesc());
        imageView.setImageResource(item.getImageResId());
        priceButton.setText(item.getPrice() == 0.0 ?
                getString(R.string.get) : String.format("$%.2f", item.getPrice()));

        priceButton.setOnClickListener(v -> {
            viewHolder.getAdapter().removeModel(viewModel);
        });
    }
}
