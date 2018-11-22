package io.inchtime.recyclerkit.example.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import io.inchtime.recyclerkit.RecyclerAdapter;
import io.inchtime.recyclerkit.RecyclerKit;
import io.inchtime.recyclerkit.example.R;
import kotlin.Unit;

import java.util.ArrayList;


public class JavaExampleActivity extends AppCompatActivity {

    private RecyclerAdapter adapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_example);
        setupRecyclerView();
        display();
    }

    private void setupRecyclerView() {

        adapter = RecyclerKit.INSTANCE
                .adapter(this, 2)
                .recyclerView(R.id.recyclerView)
                .withLinearLayout(GridLayoutManager.VERTICAL, false)
                .modelViewBind((index, viewModel, viewHolder) -> Unit.INSTANCE)
                .build();

    }

    private void display() {
        ArrayList<RecyclerAdapter.ViewModel> items = new ArrayList<>();
        adapter.setModels(items);
    }
}
