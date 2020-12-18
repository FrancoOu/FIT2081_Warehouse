package com.example.firstapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import provider.ItemViewModel;

public class AnotherActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MyAdapter myAdapter;
    ItemViewModel viewModel;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_layout);

        toolbar = findViewById(R.id.toolbar_2);

        setSupportActionBar(toolbar);

        recyclerView=findViewById(R.id.recycler_id);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter= new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        viewModel= new ViewModelProvider(this).get(ItemViewModel.class);
        viewModel.getAllItems().observe(this,newData ->{
            myAdapter.setData(newData);
            myAdapter.notifyDataSetChanged();
        });
    }
}
