package io.inchtime.recyclerkit.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import io.inchtime.recyclerkit.RecyclerAdapter;

import java.util.ArrayList;

public class JavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupRecyclerView();
    }

    private void setupRecyclerView() {

        int spanCount = 4;
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        RecyclerAdapter adapter = new RecyclerAdapter(this, spanCount);

        layoutManager.setSpanSizeLookup(adapter.getSpanSizeLookup());

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        ArrayList<RecyclerAdapter.Model> models = new ArrayList<>();
        adapter.addItems(models);
    }

}
