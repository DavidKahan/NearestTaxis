package com.kahan.david.nearesttaxis.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kahan.david.nearesttaxis.R;
import com.kahan.david.nearesttaxis.model.StubTaxisRepository;


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
        viewModel.setTaxisRepository(StubTaxisRepository.getInstance());

        mRecyclerView = findViewById(R.id.taxis_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

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
        handler.removeCallbacks(runnable); //stop handler when activity is not visible
        super.onPause();
    }
}
