package io.inchtime.recyclerkit.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import io.inchtime.recyclerkit.RecyclerKit;
import kotlin.Unit;


public class JavaExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecyclerKit.INSTANCE.adapter(this, 2)
                .modelViewBind((x,y,z)-> Unit.INSTANCE);

    }
}
