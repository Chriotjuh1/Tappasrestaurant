package com.example.tapasrestaurant;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivityView2 extends AppCompatActivity {


    private RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;
    RecycleViewAdapter recycleViewAdapter;

    int []arr={R.drawable.ic_history_background};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycleview);
        layoutManager=new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recycleViewAdapter=new RecycleViewAdapter(arr);


        recyclerView.setAdapter(recycleViewAdapter);

        recyclerView.setHasFixedSize(true);


    }
}