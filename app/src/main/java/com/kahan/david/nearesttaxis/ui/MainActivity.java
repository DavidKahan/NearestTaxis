package com.kahan.david.nearesttaxis.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kahan.david.nearesttaxis.R;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Handler handler;
    private Runnable runnable;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mRecyclerView = findViewById(R.id.taxis_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new TaxisAdapter(viewModel.getTaxis().getValue());

        mRecyclerView.setAdapter(mAdapter);

        viewModel.getTaxis().observe(this, taxis -> {
            // update UI
            ((TaxisAdapter)mAdapter).setTaxiList(taxis);
            mAdapter.notifyDataSetChanged();
        });
    }


    @Override
    protected void onResume() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                viewModel.loadTaxis();
                handler.postDelayed(this, 5000);
            }
        };
        handler.postDelayed(runnable, 5000);
        super.onResume();
    }

    @Override
    protected void onPause() {
        handler.removeCallbacks(runnable); //stop handler when activity not visible
        super.onPause();
    }
}
